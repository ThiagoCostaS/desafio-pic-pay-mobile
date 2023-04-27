package com.thiagoc.desafiopicpay.application.di

import com.thiagoc.desafiopicpay.domain.usecases.GetUsersLocalUseCase
import com.thiagoc.desafiopicpay.domain.usecases.GetUsersUseCase
import com.thiagoc.desafiopicpay.domain.usecases.SaveUsersLocalUseCase
import org.koin.dsl.module

val useCasesModule = module {
    factory {
        GetUsersUseCase(
            get()
        )
    }

    factory {
        GetUsersLocalUseCase(get())
    }

    factory { SaveUsersLocalUseCase(get()) }
}
