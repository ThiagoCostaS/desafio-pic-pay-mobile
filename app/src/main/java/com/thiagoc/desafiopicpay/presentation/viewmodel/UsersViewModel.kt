package com.thiagoc.desafiopicpay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoc.desafiopicpay.core.runCatching
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersLocalUseCase
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersUseCase
import com.thiagoc.desafiopicpay.domain.usecases.SaveUsersLocalUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersLocalUseCase: GetUsersLocalUseCase,
    private val saveUsersLocalUseCase: SaveUsersLocalUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData<UserListViewState>(UserListViewState.Loading)
    val viewState: LiveData<UserListViewState> = _viewState

    fun dispatchAction(action: UserListViewAction) {
        when (action) {
            UserListViewAction.GetUsers -> getUsers()
        }
    }

    private fun getUsers() = viewModelScope.launch(Dispatchers.IO) {
        _viewState.postValue(UserListViewState.Loading)

        runCatching(
            dispatcher = Dispatchers.Default,
            execute = {
                if (getUsersLocalUseCase().isNotEmpty()) {
                    _viewState.postValue(UserListViewState.ShowUsers(getUsersLocalUseCase()))
                }
                getUsersUseCase()
            },
            onFailure = {
                _viewState.postValue(UserListViewState.Error(it.message))
            },
            onSuccess = { users ->
                viewModelScope.launch(Dispatchers.IO) {
                    saveUsersLocalUseCase(users)
                }
                _viewState.postValue(UserListViewState.ShowUsers(users))
            }
        )
    }
}


