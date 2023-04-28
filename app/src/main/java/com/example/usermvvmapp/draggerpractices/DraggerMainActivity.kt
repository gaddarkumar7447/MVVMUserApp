package com.example.usermvvmapp.draggerpractices

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.usermvvmapp.activity.MainActivity
import com.example.usermvvmapp.databinding.ActivityDraggerMainBinding
import com.example.usermvvmapp.draggerpractices.component.DaggerUserRegistrationComponent
import com.example.usermvvmapp.draggerpractices.di.UserRegistration
import com.example.usermvvmapp.draggerpractices.module.UserRepositoryModule
import javax.inject.Inject

class DraggerMainActivity : AppCompatActivity() {
    @Inject
    lateinit var userRegistration : UserRegistration

    private lateinit var binding : ActivityDraggerMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDraggerMainBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        val viewModel = MainActivity.fakerViewModule

        viewModel.getSingle()
        viewModel.getSingle.observe(this){ it ->
            Log.d("Image1", it.image+" -> ${it.id} a")
        }

        val component = DaggerUserRegistrationComponent.builder()
            .userRepositoryModule(UserRepositoryModule(2))
            .build()
        component.inject(this)
        /*val userRegistration = component.getUserRegistrationObject()*/
        userRegistration.userRegistration("gaddarkumar7447", "Gaddar Kumar Chaudhary")

        /*val emailService = component.emailService()
        emailService.sendUser("gaddar", "Gaddar")*/
    }
}