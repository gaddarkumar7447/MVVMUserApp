package com.example.usermvvmapp.paging.network

import com.example.usermvvmapp.paging.pagingmodel.ApiResultData
import com.example.usermvvmapp.paging.pagingmodel.PagingModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("character")
    suspend fun getPagingCharacter(@Query("page") page : Int) : Response<PagingModel>
}