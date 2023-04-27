package com.thiagoc.desafiopicpay.data.repositories

import com.thiagoc.desafiopicpay.data.local.db.mapper.toDomain
import com.thiagoc.desafiopicpay.data.local.db.mapper.toEntity
import com.thiagoc.desafiopicpay.data.local.source.UserDataSourceLocal
import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.domain.UserRepositoryLocal
import com.thiagoc.desafiopicpay.extensions.getOrThrowDomainError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking


class UserRepositoryLocalImplementation(private val localDataSource: UserDataSourceLocal) :
    UserRepositoryLocal {
    override suspend fun getUsers(): List<UserDomain> = runBlocking(Dispatchers.IO) {
        runCatching { localDataSource.getAllUsers() }
            .getOrThrowDomainError()
            .map { it.toDomain() }
    }

    override suspend fun saveUsers(userList: List<UserDomain>) {
        runBlocking(Dispatchers.IO) {
            runCatching { localDataSource.saveUsers(userList.map { it.toEntity() }) }
                .getOrThrowDomainError()
        }
    }
}