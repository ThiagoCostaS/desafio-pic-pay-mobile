package com.thiagoc.desafiopicpay.data.remote.mapper

import com.thiagoc.desafiopicpay.data.remote.model.User
import com.thiagoc.desafiopicpay.domain.UserDomain

fun User.toDomain() = UserDomain(
    img = img,
    name = name,
    id = id,
    username = username)
