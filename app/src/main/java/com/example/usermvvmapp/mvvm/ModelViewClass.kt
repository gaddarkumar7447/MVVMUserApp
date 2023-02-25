package com.example.usermvvmapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usermvvmapp.model.Users
import kotlinx.coroutines.launch

class ModelViewClass(private val respository : Repository) : ViewModel(){

    init {
        viewModelScope.launch {
            respository.getAllUserData()
        }
    }

    val dataUser : LiveData<Users>
    get() = respository.userData

}