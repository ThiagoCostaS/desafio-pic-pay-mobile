package com.thiagoc.desafiopicpay.data.remote.source

import com.thiagoc.desafiopicpay.data.remote.model.User

interface UserDataSource {

    suspend fun getAllUsers() : List<User>
}