package com.example.usermvvmapp.api

import com.example.usermvvmapp.model.Users
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UsersInterface {
    // /api/?results=200

    @GET("api")
    suspend fun getUserData(@Query("results") result: Int) : Response<Users>

}