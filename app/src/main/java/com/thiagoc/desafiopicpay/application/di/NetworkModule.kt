package com.thiagoc.desafiopicpay.application.di

import com.thiagoc.desafiopicpay.data.remote.network.ApiService
import com.thiagoc.desafiopicpay.data.remote.network.PicPayService
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module {
    single { ApiService.initRetrofit()}
    single {get<Retrofit>().create(PicPayService::class.java)}
}