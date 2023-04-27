package com.thiagoc.desafiopicpay.data.local.source

import com.thiagoc.desafiopicpay.data.local.db.entity.UserEntity

interface UserDataSourceLocal {
    suspend fun getAllUsers() : List<UserEntity>

    suspend fun saveUsers(userList: List<UserEntity>)

}



