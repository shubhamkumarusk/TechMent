package com.example.techmintshubhamkumar.retrofit

import com.example.techmintshubhamkumar.models.GitHubResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {

    @GET("search/repositories")
    suspend fun searchRepositories(
        @Query("q") query: String = "friendsnetwork"
    ): Response<GitHubResponse>

}