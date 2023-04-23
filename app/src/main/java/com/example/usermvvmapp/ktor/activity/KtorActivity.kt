package com.example.usermvvmapp.ktor.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usermvvmapp.databinding.ActivityKtorBinding
import com.example.usermvvmapp.ktor.adapter.KtorAdapter
import com.example.usermvvmapp.ktor.mainrepo.MainRepository
import com.example.usermvvmapp.ktor.network.ApiServices
import com.example.usermvvmapp.ktor.viewmodel.KtorViewModel
import com.example.usermvvmapp.ktor.viewmodel.KtorViewModelFactory
import com.example.usermvvmapp.utilities.ApiResponce

class KtorActivity : AppCompatActivity() {
    private lateinit var ktorAdapter: KtorAdapter
    private lateinit var viewModel: KtorViewModel
    private lateinit var binding : ActivityKtorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKtorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ktorAdapter = KtorAdapter()
        initialiseViewModel()

        viewModel.getPostData()

        setData()
    }

    private fun initialiseViewModel() {
        viewModel = ViewModelProvider(this, KtorViewModelFactory(MainRepository(ApiServices())))[KtorViewModel::class.java]
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData() {
        viewModel.getLiveData.observe(this){
            when(it){
                is ApiResponce.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ApiResponce.Success ->{
                    ktorAdapter.submitList(it.data)
                    binding.ktorRecyclerView.layoutManager = LinearLayoutManager(this)
                    binding.ktorRecyclerView.adapter = ktorAdapter
                    binding.ktorRecyclerView.setHasFixedSize(true)
                    ktorAdapter.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                }
                is ApiResponce.Error ->{
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}