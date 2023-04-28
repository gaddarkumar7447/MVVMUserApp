package com.example.usermvvmapp.draggerpractices.di

import android.util.Log
import javax.inject.Inject

interface NotificationServices{
    fun sendUser(email : String, name : String)
}

class EmailService @Inject constructor() : NotificationServices{
    override fun sendUser(email : String, name : String){
        Log.d("Show", email + "Email")
    }
}

class MessageSend(private val retry : Int) : NotificationServices{
    override fun sendUser(email: String, name: String) {
        Log.d("Show", email + "Send  $retry")
    }
}