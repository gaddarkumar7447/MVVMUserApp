package com.example.usermvvmapp.ktor.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usermvvmapp.ktor.mainrepo.MainRepository
import com.example.usermvvmapp.ktor.model.Post
import com.example.usermvvmapp.utilities.ApiResponce
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class KtorViewModel(private val mainRepository: MainRepository) : ViewModel() {
    private val getMutableLiveData = MutableLiveData<ApiResponce<List<Post>>>()
    val getLiveData: LiveData<ApiResponce<List<Post>>> get() = getMutableLiveData

    fun getPostData() = viewModelScope.launch {
        mainRepository.getPost()
            .onStart {
                getMutableLiveData.postValue(ApiResponce.Loading())
            }.catch {
                getMutableLiveData.postValue(ApiResponce.Error("Some error occurs"))
            }.collect {
                getMutableLiveData.postValue(ApiResponce.Success(it))
            }
    }
}