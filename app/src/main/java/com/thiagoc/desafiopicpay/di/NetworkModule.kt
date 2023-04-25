package com.thiagoc.desafiopicpay.di

import com.thiagoc.desafiopicpay.remote.network.ApiService
import com.thiagoc.desafiopicpay.remote.network.PicPayService
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofitModule = module {
    single { ApiService.initRetrofit()}
    single {get<Retrofit>().create(PicPayService::class.java)}
}