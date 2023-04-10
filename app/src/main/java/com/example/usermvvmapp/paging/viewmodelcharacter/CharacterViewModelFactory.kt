package com.example.usermvvmapp.paging.viewmodelcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usermvvmapp.paging.network.ApiServices

class CharacterViewModelFactory(private val apiServices: ApiServices) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterViewModel(apiServices) as T
    }
}