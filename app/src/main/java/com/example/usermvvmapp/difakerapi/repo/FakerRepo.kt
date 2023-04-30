package com.example.usermvvmapp.difakerapi.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.usermvvmapp.difakerapi.model.ModalFaker
import com.example.usermvvmapp.difakerapi.retorfit.FakerApi
import com.example.usermvvmapp.extension.ExtensionClass.isInternetAvailable
import javax.inject.Inject

class FakerRepo @Inject constructor(private val fakerApi: FakerApi, private val context: Context) {
    private val fakerMutableLiveData  = MutableLiveData<List<ModalFaker>?>()
    val fakerLiveData : MutableLiveData<List<ModalFaker>?> = fakerMutableLiveData

    suspend fun getFaker(){
        if (isInternetAvailable(context)){
            val result = fakerApi.getProducts()
            if (result.isSuccessful && result.body() != null){
                fakerMutableLiveData.postValue(result.body())
            }
        }else{
            fakerMutableLiveData.postValue(null)
        }
    }


    private val getSingleMutableLiveData  = MutableLiveData<ModalFaker>()
    val getSingleLiveData : LiveData<ModalFaker> = getSingleMutableLiveData

    suspend fun getSingle(){
        val result = fakerApi.getSingleProduct()
        if (result.isSuccessful && result.body() != null){
            getSingleMutableLiveData.postValue(result.body())
        }
    }
}