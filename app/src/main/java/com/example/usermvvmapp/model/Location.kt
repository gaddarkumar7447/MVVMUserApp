package com.example.usermvvmapp.model

data class Location(
    val city: String ?= null,
    val country: String ?= null,
    val state: String ?= null,
    val street: Street ?= null,
    val postcode : String ?= null
)