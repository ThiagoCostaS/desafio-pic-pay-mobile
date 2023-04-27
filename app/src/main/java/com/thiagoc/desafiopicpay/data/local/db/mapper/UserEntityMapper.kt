package com.thiagoc.desafiopicpay.data.local.db.mapper

import com.thiagoc.desafiopicpay.data.local.db.entity.UserEntity
import com.thiagoc.desafiopicpay.domain.UserDomain

fun UserEntity.toDomain() = UserDomain(
    img = img,
    name = name,
    id = id,
    username = username)

fun UserDomain.toEntity() = UserEntity(
    img = img,
    name = name,
    id = id,
    username = username
)