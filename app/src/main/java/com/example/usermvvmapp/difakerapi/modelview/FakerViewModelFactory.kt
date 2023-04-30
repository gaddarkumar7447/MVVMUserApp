package com.example.usermvvmapp.difakerapi.modelview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usermvvmapp.difakerapi.repo.FakerRepo
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class FakerViewModelFactory @Inject constructor(private val fakerRepo: FakerRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FakerModuleView(fakerRepo) as T
    }
}