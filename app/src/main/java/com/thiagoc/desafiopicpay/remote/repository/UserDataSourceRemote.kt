package com.thiagoc.desafiopicpay.remote.repository

import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.extensions.getOrThrowDomainError
import com.thiagoc.desafiopicpay.remote.mapper.toDomain
import com.thiagoc.desafiopicpay.remote.network.PicPayService
import com.thiagoc.desafiopicpay.remote.source.UserDataSource

class UserDataSourceRemote(private val service: PicPayService) : UserDataSource {
    override suspend fun getAllUsers(): List<UserDomain> {
        return runCatching { service.getUsers() }
            .getOrThrowDomainError()
            .map { it.toDomain() }
    }
}