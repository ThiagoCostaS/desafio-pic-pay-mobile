package com.thiagoc.desafiopicpay.domain.usecases

import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.domain.UserRepositoryLocal

class SaveUsersLocalUseCase(private val userRepositoryLocal: UserRepositoryLocal) {
    suspend operator fun invoke(userList: List<UserDomain>) {
        return userRepositoryLocal.saveUsers(userList)
    }
}