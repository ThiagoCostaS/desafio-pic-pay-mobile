package com.thiagoc.desafiopicpay.domain.usecases

import com.thiagoc.desafiopicpay.domain.UserRepository
import com.thiagoc.desafiopicpay.domain.UserDomain

class GetUsersUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(): List<UserDomain> {
        return repository.getUsers()
    }
}