package com.example.usermvvmapp.draggerpractices.component

import com.example.usermvvmapp.draggerpractices.DraggerMainActivity
import com.example.usermvvmapp.draggerpractices.module.UserRepositoryModule
import dagger.Component

@Component(modules = [UserRepositoryModule::class])
interface UserRegistrationComponent {

    /*fun getUserRegistrationObject() : UserRegistration
    fun emailService() : EmailService*/

    fun inject(main : DraggerMainActivity)
}