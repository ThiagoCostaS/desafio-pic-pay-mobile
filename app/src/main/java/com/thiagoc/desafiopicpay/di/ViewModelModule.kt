package com.thiagoc.desafiopicpay.di

import com.thiagoc.desafiopicpay.presentation.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        UsersViewModel(get())
    }
}