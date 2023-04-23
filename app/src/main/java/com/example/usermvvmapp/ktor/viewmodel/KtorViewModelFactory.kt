package com.example.usermvvmapp.ktor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usermvvmapp.ktor.mainrepo.MainRepository

class KtorViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return KtorViewModel(mainRepository) as T
    }
}