package com.thiagoc.desafiopicpay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoc.desafiopicpay.core.runCatching
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UsersViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

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
                getUsersUseCase()
            },
            onFailure = {
                _viewState.postValue(UserListViewState.Error(it.message))
            },
            onSuccess = { users ->
                _viewState.postValue(UserListViewState.ShowUsers(users))
            }
        )
    }
}


