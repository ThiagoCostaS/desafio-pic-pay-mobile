package com.thiagoc.desafiopicpay.di

import com.thiagoc.desafiopicpay.domain.UserRepository
import com.thiagoc.desafiopicpay.data.repositories.UserRepositoryImplementation
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImplementation(get()) }
}