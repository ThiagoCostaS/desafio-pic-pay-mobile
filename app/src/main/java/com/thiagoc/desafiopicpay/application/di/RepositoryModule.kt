package com.thiagoc.desafiopicpay.application.di

import com.thiagoc.desafiopicpay.domain.UserRepository
import com.thiagoc.desafiopicpay.data.repositories.UserRepositoryImplementation
import com.thiagoc.desafiopicpay.data.repositories.UserRepositoryLocalImplementation
import com.thiagoc.desafiopicpay.domain.UserRepositoryLocal
import org.koin.dsl.module

val repositoryModule = module {
    single<UserRepository> { UserRepositoryImplementation(get()) }

    single<UserRepositoryLocal> { UserRepositoryLocalImplementation(get()) }
}