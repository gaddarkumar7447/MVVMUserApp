package com.example.usermvvmapp.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.usermvvmapp.databinding.ActivityShowUserDetailsBinding

class ShowUserDetails : AppCompatActivity() {
    private lateinit var binding : ActivityShowUserDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showTheDetails()
    }

    @SuppressLint("SetTextI18n")
    private fun showTheDetails() {
        val image = intent.extras?.getString("image")
        val name = intent.extras?.getString("name").toString()
        val phone = intent.extras?.getString("phone")
        val email = intent.extras?.getString("email")
        val location = intent.extras?.getString("location")
        val gender = intent.extras?.getString("gender")

        Glide.with(this).load(image).into(binding.imageView)
        binding.nameUser.text = "Name : $name"
        binding.emailUser.text = "Email : $email"
        binding.phoneUser.text = "Phone : $phone"
        binding.addressUser.text = "Address : $location"
        binding.genderUser.text = "Gender : $gender"
    }
}