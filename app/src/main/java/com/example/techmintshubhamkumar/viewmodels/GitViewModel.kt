package com.example.techmintshubhamkumar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.models.Users
import com.example.techmintshubhamkumar.repositories.Repository
import kotlinx.coroutines.launch


class GitViewModel(private val repository: Repository):ViewModel() {
    private val _query = MutableLiveData<String>("q") // Default query

    val gitRepositories = _query.switchMap { query ->
        repository.searchRepo(query).cachedIn(viewModelScope)
    }

    private val _contributors = MutableLiveData<List<Users>>()
    val contributors: LiveData<List<Users>> get() = _contributors

    fun searchRepo(query: String) {
        if (query.isNotEmpty()) {
            _query.value = query
        }
    }
    fun loadContributors(contributorsUrl: String) {
        viewModelScope.launch {
            _contributors.value = listOf()
            val response = repository.fetchContributors(contributorsUrl)
            if (response.isSuccessful) {
                _contributors.postValue(response.body() ?: listOf())

            } else {
                _contributors.postValue(listOf())
            }
        }
    }

}

class GitViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GitViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}