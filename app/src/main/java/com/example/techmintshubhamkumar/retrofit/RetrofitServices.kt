package com.example.techmintshubhamkumar.retrofit

import com.example.techmintshubhamkumar.models.GitHubResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("search/repositories")
    fun searchRepositories(
        @Query("q") query: String
    ): Call<GitHubResponse>

}