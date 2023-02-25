package com.example.usermvvmapp.model

data class Users(
    val info: Info ?= null,
    val results: List<Result> ?= null
)