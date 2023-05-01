package com.thiagoc.desafiopicpay.application.di

import com.thiagoc.desafiopicpay.core.ApiService
import com.thiagoc.desafiopicpay.data.remote.network.PicPayService
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module {
    single { ApiService.initRetrofit(BASE_URL) }
    single { get<Retrofit>().create(PicPayService::class.java) }
}

private const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"

