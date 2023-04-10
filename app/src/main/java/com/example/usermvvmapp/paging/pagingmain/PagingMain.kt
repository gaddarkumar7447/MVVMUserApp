package com.example.usermvvmapp.paging.pagingmain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.usermvvmapp.paging.network.ApiServices
import com.example.usermvvmapp.paging.pagingmodel.ApiResultData

class PagingMain(private val apiServices: ApiServices) : PagingSource<Int, ApiResultData>() {
    override fun getRefreshKey(state: PagingState<Int, ApiResultData>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ApiResultData> {
        return try {
            val currentPage = params.key ?: 1
            val responce =apiServices.getPagingCharacter(currentPage)
            val responceData = mutableListOf<ApiResultData>()
            val data = responce.body()?.results?: emptyList()
            responceData.addAll(data)

            LoadResult.Page(
                data = responceData,
                prevKey = if (currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        }catch (e : Exception){
            LoadResult.Error(e.fillInStackTrace())
        }
    }

}