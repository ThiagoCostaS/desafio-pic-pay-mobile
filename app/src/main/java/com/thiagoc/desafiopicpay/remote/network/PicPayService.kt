package com.thiagoc.desafiopicpay.remote.network

import com.thiagoc.desafiopicpay.remote.model.User
import retrofit2.http.GET


interface PicPayService {
    @GET("users")
    suspend fun getUsers(): List<User>
}