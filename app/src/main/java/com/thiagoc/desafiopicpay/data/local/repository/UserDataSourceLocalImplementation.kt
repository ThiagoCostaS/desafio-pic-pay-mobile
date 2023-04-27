package com.thiagoc.desafiopicpay.data.local.repository

import com.thiagoc.desafiopicpay.data.local.db.dao.UserDao
import com.thiagoc.desafiopicpay.data.local.db.entity.UserEntity
import com.thiagoc.desafiopicpay.data.local.source.UserDataSourceLocal

class UserDataSourceLocalImplementation(private val userDao: UserDao) : UserDataSourceLocal {
    override suspend fun getAllUsers(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    override suspend fun saveUsers(userList: List<UserEntity>) {
        return userDao.insertAll(userList)
    }
}