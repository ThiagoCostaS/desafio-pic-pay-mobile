package com.thiagoc.desafiopicpay.di

import com.thiagoc.desafiopicpay.data.remote.repository.UserDataSourceRemoteImplementation
import com.thiagoc.desafiopicpay.data.remote.source.UserDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<UserDataSource> {
        UserDataSourceRemoteImplementation(
            get())
    }
}