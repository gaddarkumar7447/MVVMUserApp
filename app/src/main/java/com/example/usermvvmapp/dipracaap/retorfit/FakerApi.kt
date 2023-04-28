package com.example.usermvvmapp.dipracaap.retorfit

import com.example.usermvvmapp.dipracaap.model.ModalFaker
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Named

@Named("fakerUser")
interface FakerApi {

    @GET("products")
    suspend fun getProducts() : Response<List<ModalFaker>>

    @GET("/products/1")
    suspend fun getSingleProduct() : Response<ModalFaker>
}