package com.thiagoc.desafiopicpay.data.repositories

import com.thiagoc.desafiopicpay.data.UserRepository
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.remote.repository.UserDataSourceRemote
import kotlinx.coroutines.runBlocking

class UserRepositoryImplementation(private val remoteDataSource: UserDataSourceRemote) :
    UserRepository {
    override suspend fun getUsers(): List<UserDomain> =
        runBlocking {
            remoteDataSource.getAllUsers()
        }
}