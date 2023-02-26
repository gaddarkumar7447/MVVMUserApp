package com.example.usermvvmapp.extension

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.usermvvmapp.model.Location
import com.example.usermvvmapp.model.Name

object ExtensionClass {
    fun Name.getAllName() : String{
        return this.title +" "+this.first+" "+this.last
    }

    fun Location.getUserLocation() : String{
        return this.street?.number.toString() +" "+this.street?.name.toString() +" "+this.city +" "+this.state +" "+this.country +" "+this.postcode.toString()
    }

    @SuppressLint("ServiceCast")
    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }
}