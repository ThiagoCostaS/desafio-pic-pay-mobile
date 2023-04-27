package com.thiagoc.desafiopicpay.factory

import com.thiagoc.desafiopicpay.domain.UserDomain

object UsersFactory {

    val usersList = listOf(
        UserDomain("google.com", "Thiago", 2, "thiagoc"),
        UserDomain("google.com", "Thiago", 2, "thiagoc"),
        UserDomain("google.com", "Thiago3", 4, "thiagocst"))
}