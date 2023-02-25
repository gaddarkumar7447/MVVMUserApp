package com.example.usermvvmapp.extension

import com.example.usermvvmapp.model.Location
import com.example.usermvvmapp.model.Name

object ExtensionClass {
    fun Name.getAllName() : String{
        return this.title +" "+this.first+" "+this.last
    }

    fun Location.getUserLocation() : String{
        return this.street?.number.toString() +" "+this.street?.name.toString() +" "+this.city +" "+this.state +" "+this.country +" "+this.postcode.toString()
    }
}