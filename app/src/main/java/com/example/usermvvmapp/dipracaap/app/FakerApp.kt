package com.example.usermvvmapp.dipracaap.app

import android.app.Application
import com.example.usermvvmapp.dipracaap.ApplicationComponent
import com.example.usermvvmapp.dipracaap.DaggerApplicationComponent
import com.example.usermvvmapp.dipracaap.Module

class FakerApp : Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().module(Module(applicationContext)).build()


    }
}