package com.example.usermvvmapp.ktor.mainrepo

import com.example.usermvvmapp.ktor.model.Post
import com.example.usermvvmapp.ktor.network.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MainRepository(private val apiServices: ApiServices) {
    fun getPost() : Flow<List<Post>> = flow {
        emit(apiServices.getPost())
    }.flowOn(Dispatchers.IO)
}