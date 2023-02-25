package com.example.usermvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usermvvmapp.adapter.AdapterUser
import com.example.usermvvmapp.api.ApiUtilities
import com.example.usermvvmapp.api.UsersInterface
import com.example.usermvvmapp.apiresult.ApiResult
import com.example.usermvvmapp.databinding.ActivityMainBinding
import com.example.usermvvmapp.model.Users
import com.example.usermvvmapp.model.Result
import com.example.usermvvmapp.mvvm.ModelViewClass
import com.example.usermvvmapp.mvvm.Repository
import com.example.usermvvmapp.mvvm.ViewModelFactoryClass
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: AdapterUser
    private lateinit var users: MutableList<Result>
    private lateinit var modelViewClass: ModelViewClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        users = mutableListOf()

        /*setUpRecyclerView()
        loadDataFromViewModel()*/

        binding.recyclerViewVeil.addVeiledItems(12)
        binding.recyclerViewVeil.apply {
            adapterUser = AdapterUser(this@MainActivity, users)
            this.veil()
            this.setAdapter(adapterUser)
        }


        val instance = ApiUtilities.getApiUtilities().create(UsersInterface::class.java)
        val respository = Repository(instance)

        modelViewClass = ViewModelProvider(this@MainActivity, ViewModelFactoryClass(respository))[ModelViewClass::class.java]

        callViewModelMethod(modelViewClass)

        /*MainScope().launch {
            val instance = ApiUtilities.getApiUtilities().create(UsersInterface::class.java)
            val data = instance.getUserData(200)
            val respository = Repository(instance)
            modelViewClass = ViewModelProvider(this@MainActivity, ViewModelFactoryClass(respository))[ModelViewClass::class.java]
            modelViewClass.dataUser.observe(this@MainActivity, Observer {
                val result = it.results
                users = result as MutableList<Result>
                Log.d("UsersData", "User: $users")
                //users.add(it.results)
                adapterUser = AdapterUser(this@MainActivity, users)
                binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this@MainActivity))
                binding.recyclerViewVeil.unVeil()
            })
            *//*data.body()?.results?.forEach {
                users.add(it)
            }*//*
        }*/

    }

    private fun callViewModelMethod(modelViewClass: ModelViewClass) {
        modelViewClass.dataUser.observe(this@MainActivity, Observer {
            val result = it.results
            users = result as MutableList<Result>

            Log.d("UsersData", "User: $users")

            adapterUser = AdapterUser(this@MainActivity, users)
            binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this@MainActivity))
            binding.recyclerViewVeil.unVeil()
        })
    }

    /*private fun loadDataFromViewModel() {
        lifecycleScope.launch{
            modelViewClass.dataUser.observe(this@MainActivity, Observer {
                when(it){
                    is ApiResult.Success -> showData(it as MutableList<Result>)
                    is ApiResult.Error -> showError()
                    is ApiResult.Loading -> showLoading()
                }
            })
        }
    }*/

    private fun showLoading() {

    }

    private fun showError() {

    }

    private fun showData(results: List<Result>?) {

    }

    private fun setUpRecyclerView() {
        binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this))
        binding.recyclerViewVeil.addVeiledItems(12)
    }

}

