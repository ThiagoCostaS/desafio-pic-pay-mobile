package com.thiagoc.desafiopicpay.data.usecases

import com.thiagoc.desafiopicpay.data.UserRepository
import com.thiagoc.desafiopicpay.domain.UserDomain

class GetUsersUseCase(private val repository: UserRepository) {

    suspend operator fun invoke(): List<UserDomain> =
        repository.getUsers()
}