package com.example.techmintshubhamkumar.repositories

import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.models.GitHubResponse
import com.example.techmintshubhamkumar.retrofit.RetrofitHelper
import com.example.techmintshubhamkumar.retrofit.RetrofitServices
import retrofit2.Response

class Repository {
    private val api = RetrofitHelper.getInstance().create(RetrofitServices::class.java)

    suspend fun searchRepo(query:String):Response<GitHubResponse>{
        return api.searchRepositories(query)
    }

}