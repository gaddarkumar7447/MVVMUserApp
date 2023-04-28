package com.example.usermvvmapp.draggerpractices.di

import android.util.Log
import javax.inject.Inject

class UserRepository @Inject constructor() {
    fun createUser(email : String, name : String){
        Log.d("Show", "$email $name")
    }
}