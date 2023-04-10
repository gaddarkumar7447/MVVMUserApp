package com.example.usermvvmapp.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usermvvmapp.databinding.ActivityPagingMainBinding
import com.example.usermvvmapp.paging.adap.AdapterForPaging
import com.example.usermvvmapp.paging.network.ApiIntence
import com.example.usermvvmapp.paging.network.ApiServices
import com.example.usermvvmapp.paging.pagingmodel.ApiResultData
import com.example.usermvvmapp.paging.viewmodelcharacter.CharacterViewModel
import com.example.usermvvmapp.paging.viewmodelcharacter.CharacterViewModelFactory
import kotlinx.coroutines.launch

class PagingMainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPagingMainBinding
    private lateinit var pagingAdapter: AdapterForPaging
    private lateinit var result: List<ApiResultData>
    private lateinit var viewModel: CharacterViewModel
    private var isFirstTimeRefreshing = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagingMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        result = emptyList()

        pagingAdapter = AdapterForPaging()

        initializeViewModel()

        setUpRecyclerView()

        loadData()


    }

    private fun loadData() {
        lifecycleScope.launch{
            viewModel.listData.collect{
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerViewVeilForPaging.apply {
            setAdapter(pagingAdapter)
            setLayoutManager(LinearLayoutManager(this@PagingMainActivity))
            addVeiledItems(10)
        }
        pagingAdapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading){
                if (isFirstTimeRefreshing){
                    binding.recyclerViewVeilForPaging.veil()
                }else{
                    binding.progressBar.visibility = View.VISIBLE
                    binding.recyclerViewVeilForPaging.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        this.setMargins(0,0,0,100)
                    }
                }
            }else{
                if (isFirstTimeRefreshing){
                    binding.recyclerViewVeilForPaging.postDelayed({
                        binding.recyclerViewVeilForPaging.unVeil()
                        isFirstTimeRefreshing = false
                    },2000)
                }
                binding.progressBar.visibility = View.INVISIBLE
                binding.recyclerViewVeilForPaging.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    setMargins(0,0,0,0)
                }
                // if an error then show toast
                val errorState = when{
                    loadState.append is LoadState.Error ->loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.apply {
                    Toast.makeText(this@PagingMainActivity, "${this.error}", Toast.LENGTH_SHORT).show()
                }
            }

        }


    }

    private fun initializeViewModel() {
        val apiIntence = ApiIntence.apiInstance().create(ApiServices::class.java)
        viewModel = ViewModelProvider(this, CharacterViewModelFactory(apiIntence))[CharacterViewModel::class.java]
    }
}