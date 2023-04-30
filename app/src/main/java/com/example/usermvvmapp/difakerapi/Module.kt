package com.example.usermvvmapp.difakerapi

import android.content.Context
import com.example.usermvvmapp.difakerapi.retorfit.FakerApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class Module(private val context: Context) {

    @Singleton
    @Provides
    fun getRetrofitInstance() : Retrofit{
        return Retrofit.Builder().baseUrl("https://fakestoreapi.com/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClass(retrofit: Retrofit) : FakerApi{
        return retrofit.create(FakerApi::class.java)
    }


    @Provides
    fun getContext() = context
}