package com.example.techmintshubhamkumar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.repositories.Repository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest


class GitViewModel(private val repository: Repository):ViewModel() {
    private val _query = MutableLiveData<String>("q") // Default query
    val gitRepositories = _query.switchMap { query ->
        repository.searchRepo(query).cachedIn(viewModelScope)
    }

    fun searchRepo(query: String) {
        if (query.isNotEmpty()) {
            _query.value = query
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