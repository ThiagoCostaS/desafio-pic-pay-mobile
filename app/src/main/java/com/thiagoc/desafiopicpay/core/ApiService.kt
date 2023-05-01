package com.thiagoc.desafiopicpay.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    fun initRetrofit(baseUrl: String) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}