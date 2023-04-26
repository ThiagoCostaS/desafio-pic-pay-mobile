package com.thiagoc.desafiopicpay.di

import com.thiagoc.desafiopicpay.data.usecases.GetUsersUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory {
        GetUsersUseCase(
            get())
    }
}
