package com.example.usermvvmapp.paging.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiIntence {
    fun apiInstance(context: Context) : Retrofit{
        val client = OkHttpClient.Builder().addInterceptor(
            ChuckerInterceptor.Builder(context = context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        ).build()
        return Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/").client(client).addConverterFactory(GsonConverterFactory.create()).build()
    }
}