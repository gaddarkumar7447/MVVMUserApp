package com.example.usermvvmapp.model

data class Result(
    val cell: String ?= null,
    val email: String ?= null,
    val gender: String ?= null,
    val location: Location ?= null,
    val name: Name ?= null,
    val nat: String ?= null,
    val phone: String ?= null,
    val picture: Picture ?= null
)