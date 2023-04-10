package com.example.usermvvmapp.paging.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiIntence {
    fun apiInstance() : Retrofit{
        return Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").addConverterFactory(GsonConverterFactory.create()).build()
    }
}