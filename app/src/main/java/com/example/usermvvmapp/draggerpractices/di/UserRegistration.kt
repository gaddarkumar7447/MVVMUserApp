package com.example.usermvvmapp.draggerpractices.di

import com.example.usermvvmapp.draggerpractices.MessageQualifier
import javax.inject.Inject

class UserRegistration @Inject constructor(
    private val userRepository: UserRepository,
    @MessageQualifier private val emailService: NotificationServices
) {
    fun userRegistration(email: String, name: String) {
        userRepository.createUser(email, name)
        emailService.sendUser(email, name)
    }
}