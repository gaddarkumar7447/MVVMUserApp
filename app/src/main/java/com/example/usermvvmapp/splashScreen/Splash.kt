package com.example.usermvvmapp.splashScreen

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.usermvvmapp.MainActivity
import com.example.usermvvmapp.R
import com.example.usermvvmapp.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startActivity(Intent(this@Splash, MainActivity::class.java))
        finish()

    }
}