package com.thiagoc.desafiopicpay.domain

interface UserRepositoryLocal {

    suspend fun getUsers(): List<UserDomain>

    suspend fun saveUsers(userList: List<UserDomain>)

}
