package com.thiagoc.desafiopicpay.remote.mapper

import com.thiagoc.desafiopicpay.domain.UserDomain
import com.thiagoc.desafiopicpay.remote.model.User

fun User.toDomain() = UserDomain(
    img = img,
    name = name,
    id = id,
    username = username
)