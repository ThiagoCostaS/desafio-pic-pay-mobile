package com.thiagoc.desafiopicpay.data.repositories

import com.thiagoc.desafiopicpay.data.UserRepository
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.remote.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UserRepositoryImplementation(private val remoteDataSource: UserDataSource) :
    UserRepository {
    override suspend fun getUsers(): List<UserDomain> =
        runBlocking(Dispatchers.IO) {
            remoteDataSource.getAllUsers()
        }
}