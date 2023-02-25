package com.example.usermvvmapp.apiresult

import com.example.usermvvmapp.model.Users

sealed class ApiResult{
    class Success(val users: Users) : ApiResult()
    object Error : ApiResult()
    object Loading : ApiResult()
}
