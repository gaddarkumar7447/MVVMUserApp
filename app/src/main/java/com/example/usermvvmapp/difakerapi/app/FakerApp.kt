package com.example.usermvvmapp.difakerapi.app

import android.app.Application
import com.example.usermvvmapp.difakerapi.ApplicationComponent
import com.example.usermvvmapp.difakerapi.DaggerApplicationComponent
import com.example.usermvvmapp.difakerapi.Module

class FakerApp : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().module(Module(applicationContext)).build()
    }
}