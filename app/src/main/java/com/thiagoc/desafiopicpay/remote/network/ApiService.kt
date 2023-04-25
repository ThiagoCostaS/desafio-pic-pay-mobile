package com.thiagoc.desafiopicpay.remote.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val okHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    fun initRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private const val BASE_URL =   "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
}