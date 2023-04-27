package com.thiagoc.desafiopicpay.application.di

import com.thiagoc.desafiopicpay.data.local.repository.UserDataSourceLocalImplementation
import com.thiagoc.desafiopicpay.data.local.source.UserDataSourceLocal
import com.thiagoc.desafiopicpay.data.remote.repository.UserDataSourceRemoteImplementation
import com.thiagoc.desafiopicpay.data.remote.source.UserDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single<UserDataSource> {
        UserDataSourceRemoteImplementation(
            get())
    }

    single<UserDataSourceLocal>{
        UserDataSourceLocalImplementation(get())
    }
}