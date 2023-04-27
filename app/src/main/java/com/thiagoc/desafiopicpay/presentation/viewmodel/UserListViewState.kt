package com.thiagoc.desafiopicpay.presentation.viewmodel

import com.thiagoc.desafiopicpay.domain.UserDomain

sealed class UserListViewState{

    object Loading : UserListViewState()

    data class ShowUsers(val users: List<UserDomain>) : UserListViewState()

    data class Error(val message: String?) : UserListViewState()
}
