package com.example.usermvvmapp.dipracaap.modelview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usermvvmapp.dipracaap.model.ModalFaker
import com.example.usermvvmapp.dipracaap.repo.FakerRepo
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