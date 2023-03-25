package com.example.usermvvmapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usermvvmapp.adapter.AdapterUser
import com.example.usermvvmapp.api.ApiUtilities
import com.example.usermvvmapp.api.UsersInterface
import com.example.usermvvmapp.databinding.ActivityMainBinding
import com.example.usermvvmapp.extension.ExtensionClass
import com.example.usermvvmapp.extension.ExtensionClass.getAllName
import com.example.usermvvmapp.extension.ExtensionClass.isInternetAvailable
import com.example.usermvvmapp.model.Name
import com.example.usermvvmapp.model.Result
import com.example.usermvvmapp.model.Users
import com.example.usermvvmapp.mvvm.ModelViewClass
import com.example.usermvvmapp.mvvm.Repository
import com.example.usermvvmapp.mvvm.ViewModelFactoryClass
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar

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

        binding.recyclerViewVeil.addVeiledItems(12)
        adapterUser = AdapterUser(this@MainActivity, users)
        binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this))
        binding.recyclerViewVeil.veil()


        if (isInternetAvailable(this)){
            val instance = ApiUtilities.getApiUtilities().create(UsersInterface::class.java)
            val respository = Repository(instance)
            modelViewClass = ViewModelProvider(this@MainActivity, ViewModelFactoryClass(respository))[ModelViewClass::class.java]

            callViewModelMethod(modelViewClass)
        }else{
            Snackbar.make(binding.root, "Check internet connection...", Snackbar.LENGTH_LONG).show()
        }

        binding.searchUser.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val filteredUsers = users.filter { user ->
                    user.name?.getAllName()?.contains(p0.toString()) ?: false ||
                    user.phone?.contains(p0.toString()) ?: false
                }
                if (filteredUsers.isEmpty()){
                    Snackbar.make(binding.root, "No data found ${p0.toString()}", Snackbar.LENGTH_LONG).show()
                }

                adapterUser = AdapterUser(this@MainActivity, filteredUsers)
                binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this@MainActivity))
                binding.recyclerViewVeil.unVeil()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
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
        if (isInternetAvailable(this)){
            modelViewClass.dataUser.observe(this@MainActivity, Observer {
                val result = it.results
                users = result as MutableList<Result>

                adapterUser = AdapterUser(this@MainActivity, users)
                binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this@MainActivity))
                binding.recyclerViewVeil.unVeil()
            })
        }else{
            Snackbar.make(binding.root, "Check internet connection", Snackbar.LENGTH_LONG).show()
        }
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


    private fun setUpRecyclerView() {
        binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this))
        binding.recyclerViewVeil.addVeiledItems(12)
    }

}

