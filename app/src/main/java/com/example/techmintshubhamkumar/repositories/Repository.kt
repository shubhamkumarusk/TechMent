package com.example.techmintshubhamkumar.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.models.GitHubResponse
import com.example.techmintshubhamkumar.models.Users
import com.example.techmintshubhamkumar.paging.RepoPagingSource
import com.example.techmintshubhamkumar.retrofit.RetrofitHelper
import com.example.techmintshubhamkumar.retrofit.RetrofitServices
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class Repository {
    private val api = RetrofitHelper.getInstance().create(RetrofitServices::class.java)
    fun searchRepo(query: String): LiveData<PagingData<GitHubRepo>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                initialLoadSize = 10
            ),
            pagingSourceFactory = { RepoPagingSource(api, query) }
        ).liveData
    }

    suspend fun fetchContributors(url:String):Response<List<Users>>{
        return api.getContributors(url)
    }
}