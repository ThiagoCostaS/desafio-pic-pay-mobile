package com.thiagoc.desafiopicpay.domain

interface UserRepository {

    suspend fun getUsers(): List<UserDomain>
}