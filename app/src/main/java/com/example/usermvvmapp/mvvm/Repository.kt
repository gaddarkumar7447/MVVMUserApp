package com.example.usermvvmapp.mvvm

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.usermvvmapp.api.UsersInterface
import com.example.usermvvmapp.extension.ExtensionClass.isInternetAvailable
import com.example.usermvvmapp.model.Users
import com.example.usermvvmapp.utilities.ApiResponce

class Repository(private val usersInterface: UsersInterface, private val context: Context) {

    private val mutableGetData = MutableLiveData<ApiResponce<Users>>()

    val userData : LiveData<ApiResponce<Users>>
    get() = mutableGetData

    suspend fun getAllUserData(){
        if (isInternetAvailable(context)){
            try {
                mutableGetData.postValue(ApiResponce.Loading())
                val result = usersInterface.getUserData(200).body()
                if (result != null){
                    mutableGetData.postValue(ApiResponce.Success(result))
                }else{
                    mutableGetData.postValue(ApiResponce.Error("Something went to wrong"))
                }
            }catch (e : Exception){
                mutableGetData.postValue(ApiResponce.Error("Network Issue :)"))
            }
        }else{
            mutableGetData.postValue(ApiResponce.Error("Network not available"))
        }
    }
}