package com.example.usermvvmapp.paging.viewmodelcharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.usermvvmapp.paging.network.ApiServices
import com.example.usermvvmapp.paging.pagingmain.PagingMain

class CharacterViewModel(private val apiServices: ApiServices) : ViewModel(){

    val listData = Pager(PagingConfig(pageSize = 1)){PagingMain(apiServices)}.flow.cachedIn(viewModelScope)

}