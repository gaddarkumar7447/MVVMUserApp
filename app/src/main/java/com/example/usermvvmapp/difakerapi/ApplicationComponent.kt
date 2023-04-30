package com.example.usermvvmapp.difakerapi

import com.example.usermvvmapp.activity.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

}