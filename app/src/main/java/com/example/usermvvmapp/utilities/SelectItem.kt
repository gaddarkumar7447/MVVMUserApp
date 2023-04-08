package com.example.usermvvmapp.utilities

import com.example.usermvvmapp.adapter.AdapterUser
import com.example.usermvvmapp.model.Result

interface SelectItem {
    fun selectItemMethod(holder: AdapterUser.ViewHolderUser, currentPosition: Result)
}