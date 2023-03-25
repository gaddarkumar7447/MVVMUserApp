package com.example.usermvvmapp.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.usermvvmapp.api.UsersInterface
import com.example.usermvvmapp.model.Users

class Repository(private val usersInterface: UsersInterface) {

    private val mutableGetData = MutableLiveData<Users>()

    val userData : LiveData<Users>
    get() = mutableGetData

    //
    //suspend fun getUU() = usersInterface.getUserData(300)

    suspend fun getAllUserData(){
        val result = usersInterface.getUserData(200).body()
        if (result != null){
            mutableGetData.postValue(result)
        }
    }


}