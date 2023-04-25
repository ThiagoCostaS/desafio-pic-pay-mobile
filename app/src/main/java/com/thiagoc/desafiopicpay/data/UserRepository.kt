package com.thiagoc.desafiopicpay.data

import com.thiagoc.desafiopicpay.domain.UserDomain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>
}