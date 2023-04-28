package com.example.usermvvmapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usermvvmapp.adapter.AdapterUser
import com.example.usermvvmapp.api.ApiUtilities
import com.example.usermvvmapp.api.UsersInterface
import com.example.usermvvmapp.databinding.ActivityMainBinding
import com.example.usermvvmapp.dipracaap.app.FakerApp
import com.example.usermvvmapp.dipracaap.modelview.FakerModuleView
import com.example.usermvvmapp.dipracaap.modelview.FakerViewModelFactory
import com.example.usermvvmapp.draggerpractices.DraggerMainActivity
import com.example.usermvvmapp.extension.ExtensionClass.getAllName
import com.example.usermvvmapp.extension.ExtensionClass.getUserLocation
import com.example.usermvvmapp.ktor.activity.KtorActivity
import com.example.usermvvmapp.model.Result
import com.example.usermvvmapp.mvvm.ModelViewClass
import com.example.usermvvmapp.mvvm.Repository
import com.example.usermvvmapp.mvvm.ViewModelFactoryClass
import com.example.usermvvmapp.paging.ui.PagingMainActivity
import com.example.usermvvmapp.utilities.ApiResponce
import com.example.usermvvmapp.utilities.SelectItem
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SelectItem {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: AdapterUser
    private lateinit var users: MutableList<Result>
    private lateinit var modelViewClass: ModelViewClass

    @Inject
    lateinit var fakerViewModelFactory: FakerViewModelFactory

    companion object{
        lateinit var fakerViewModule: FakerModuleView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (application as FakerApp).applicationComponent.inject(this)

        fakerViewModule = ViewModelProvider(this, fakerViewModelFactory)[FakerModuleView::class.java]
        fakerViewModule.getFaker()
        fakerViewModule.fakerLiveData.observe(this){ modalFakers ->
            if (modalFakers == null){
                Log.d("Image1", "null")
            }

            modalFakers?.forEach {
                Log.d("Image1", it.image+" -> ${it.id}")
            }
        }

        users = mutableListOf()

        binding.paging.setOnClickListener { startActivity(Intent(this, PagingMainActivity::class.java)) }
        binding.ktor.setOnClickListener { startActivity(Intent(this, KtorActivity::class.java)) }
        binding.dragger.setOnClickListener { startActivity(Intent(this, DraggerMainActivity::class.java)) }

        initializeViewModel()

        callViewModelMethod()

        searchData()
    }

    private fun initializeViewModel() {
        val instance = ApiUtilities.getApiUtilities().create(UsersInterface::class.java)
        val respository = Repository(instance, this)
        modelViewClass = ViewModelProvider(this@MainActivity, ViewModelFactoryClass(respository))[ModelViewClass::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun callViewModelMethod() {
        modelViewClass.dataUser.observe(this@MainActivity, Observer {
            when (it) {
                is ApiResponce.Loading -> {
                    binding.recyclerViewVeil.addVeiledItems(12)
                    adapterUser = AdapterUser(this@MainActivity, users, this)
                    binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this))
                    binding.recyclerViewVeil.veil()
                }
                is ApiResponce.Success -> {
                    //users = it.data?.results as MutableList<Result>
                    adapterUser = it.data?.results.let { it1 -> AdapterUser(this@MainActivity, it1!!, this) }
                    binding.recyclerViewVeil.setAdapter(adapterUser, LinearLayoutManager(this@MainActivity))
                    adapterUser.notifyDataSetChanged()
                    binding.recyclerViewVeil.unVeil()
                }
                is ApiResponce.Error -> {
                    Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun selectItemMethod(holder: AdapterUser.ViewHolderUser, currentPosition: Result) {
        val intent = Intent(this, ShowUserDetails::class.java)
        with(intent) {
            putExtra("image", currentPosition.picture?.large)
            putExtra("email", currentPosition.email.toString())
            putExtra("name", currentPosition.name?.getAllName().toString())
            putExtra("phone", currentPosition.phone.toString())
            putExtra("gender", currentPosition.gender)
            putExtra("location", currentPosition.location?.getUserLocation().toString())
        }
        startActivity(intent)
    }

    private fun searchData() {
        binding.searchUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val filteredUsers = users.filter { user ->
                    user.name?.getAllName()?.contains(p0.toString()) ?: false || user.phone?.contains(p0.toString()) ?: false
                }
                if (filteredUsers.isEmpty()) {
                    Snackbar.make(binding.root, "No data found ${p0.toString()}", Snackbar.LENGTH_LONG).show()
                }

                adapterUser = AdapterUser(this@MainActivity, filteredUsers, this@MainActivity)
                binding.recyclerViewVeil.setAdapter(
                    adapterUser,
                    LinearLayoutManager(this@MainActivity)
                )
                binding.recyclerViewVeil.unVeil()
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
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


