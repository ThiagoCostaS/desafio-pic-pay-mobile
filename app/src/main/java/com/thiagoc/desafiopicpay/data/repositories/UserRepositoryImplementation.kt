package com.thiagoc.desafiopicpay.data.repositories

import com.thiagoc.desafiopicpay.data.remote.mapper.toDomain
import com.thiagoc.desafiopicpay.data.remote.source.UserDataSource
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.domain.UserRepository
import com.thiagoc.desafiopicpay.extensions.getOrThrowDomainError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class UserRepositoryImplementation(private val remoteDataSource: UserDataSource) :
    UserRepository {
    override suspend fun getUsers(): List<UserDomain> = runBlocking(Dispatchers.IO) {
        runCatching { remoteDataSource.getAllUsers() }
            .getOrThrowDomainError()
            .map { it.toDomain() }
    }
}
