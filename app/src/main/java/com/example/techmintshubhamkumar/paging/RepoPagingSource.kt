package com.example.techmintshubhamkumar.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.retrofit.RetrofitServices

class RepoPagingSource(private val api:RetrofitServices,private val query : String):PagingSource<Int,GitHubRepo>() {

    override fun getRefreshKey(state: PagingState<Int, GitHubRepo>): Int? {
        return state.anchorPosition?.let{
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepo> {
        val position = params.key ?: 1  // Default to the first page if no key is passed
        return try {
            val response = api.searchRepositories(query, position, params.loadSize)
            if (response.isSuccessful) {
                val data = response.body()?.items ?: emptyList()
                LoadResult.Page(
                    data = data,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (data.isEmpty()) null else position + 1
                )
            } else {
                LoadResult.Error(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}