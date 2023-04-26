package com.thiagoc.desafiopicpay.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagoc.desafiopicpay.data.usecases.GetUsersUseCase
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
        try {
            val users = getUsersUseCase
            _viewState.postValue(UserListViewState.ShowUsers(users.invoke()))
        } catch (e: Exception) {
            _viewState.postValue(UserListViewState.Error(e.message ?: "Unknown error"))
        }
    }
}


