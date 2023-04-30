package com.example.usermvvmapp.difakerapi.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usermvvmapp.difakerapi.model.ModalFaker
import com.example.usermvvmapp.difakerapi.repo.FakerRepo
import kotlinx.coroutines.launch

class FakerModuleView(private val fakerRepo: FakerRepo) : ViewModel() {

    fun getFaker(){
        viewModelScope.launch {
            fakerRepo.getFaker()
        }
    }
    val fakerLiveData: MutableLiveData<List<ModalFaker>?> = fakerRepo.fakerLiveData


    fun getSingle(){
        viewModelScope.launch {
            fakerRepo.getSingle()
        }
    }
    val getSingle: LiveData<ModalFaker> = fakerRepo.getSingleLiveData

}