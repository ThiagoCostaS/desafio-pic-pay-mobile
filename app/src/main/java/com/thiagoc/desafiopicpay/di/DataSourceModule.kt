package com.thiagoc.desafiopicpay.di

import com.thiagoc.desafiopicpay.remote.repository.UserDataSourceRemoteImplementation
import com.thiagoc.desafiopicpay.remote.source.UserDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<UserDataSource> {
        UserDataSourceRemoteImplementation(
            get())
    }
}