package com.thiagoc.desafiopicpay.domain.usecases

import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.domain.UserRepositoryLocal

class GetUsersLocalUseCase(private val userRepositoryLocal: UserRepositoryLocal) {
    suspend operator fun invoke(): List<UserDomain> {
        return userRepositoryLocal.getUsers()
    }
}