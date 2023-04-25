package com.thiagoc.desafiopicpay.presentation.viewmodel

import com.thiagoc.desafiopicpay.domain.UserDomain

sealed class UserListViewState{

    object Loading : UserListViewState()

    class ShowUsers(val users: List<UserDomain>) : UserListViewState()

    class Error(val message: String?) : UserListViewState()
}
