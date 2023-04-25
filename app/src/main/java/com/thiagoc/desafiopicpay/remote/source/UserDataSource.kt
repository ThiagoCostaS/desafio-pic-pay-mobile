package com.thiagoc.desafiopicpay.remote.source

import com.thiagoc.desafiopicpay.domain.UserDomain

interface UserDataSource {

    suspend fun getAllUsers() : List<UserDomain>
}