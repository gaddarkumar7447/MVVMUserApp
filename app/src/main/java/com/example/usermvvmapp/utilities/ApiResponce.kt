package com.example.usermvvmapp.utilities

sealed class ApiResponce<T>(val data : T ?= null, val message : String ?= null) {
    class Success<T>(data: T) : ApiResponce<T>(data)
    class Loading<T>() : ApiResponce<T>()
    class Error<T>(error: String) : ApiResponce<T>(message = error)
}