package com.example.usermvvmapp.draggerpractices.module

import com.example.usermvvmapp.draggerpractices.MessageQualifier
import com.example.usermvvmapp.draggerpractices.di.EmailService
import com.example.usermvvmapp.draggerpractices.di.MessageSend
import com.example.usermvvmapp.draggerpractices.di.NotificationServices
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class UserRepositoryModule(private val retry : Int) {

    @MessageQualifier
    @Provides
    fun getMessageSend() : NotificationServices{
        return MessageSend(retry)
    }

    @Named("Email")
    @Provides
    fun getEmail(emailService: EmailService) : NotificationServices{
        return emailService
    }

}