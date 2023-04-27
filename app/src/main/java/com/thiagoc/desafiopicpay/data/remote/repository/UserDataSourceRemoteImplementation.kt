package com.thiagoc.desafiopicpay.data.remote.repository

import com.thiagoc.desafiopicpay.data.remote.model.User
import com.thiagoc.desafiopicpay.data.remote.network.PicPayService
import com.thiagoc.desafiopicpay.data.remote.source.UserDataSource

class UserDataSourceRemoteImplementation(private val service: PicPayService) : UserDataSource {
    override suspend fun getAllUsers(): List<User> {
       return service.getUsers()
    }
}