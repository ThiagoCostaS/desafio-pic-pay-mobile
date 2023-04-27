package com.thiagoc.desafiopicpay.data.remote.network

import com.thiagoc.desafiopicpay.data.remote.model.User
import retrofit2.http.GET


interface PicPayService {
    @GET("users")
    suspend fun getUsers(): List<User>
}