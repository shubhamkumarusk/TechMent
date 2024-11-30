package com.example.techmintshubhamkumar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.techmintshubhamkumar.models.GitHubRepo
import com.example.techmintshubhamkumar.repositories.Repository
import kotlinx.coroutines.launch

class GitViewModel(private val repository: Repository):ViewModel() {
    private val _gitRepositories = MutableLiveData<Result<List<GitHubRepo>>>()
    val gitRepositories: LiveData<Result<List<GitHubRepo>>> = _gitRepositories

    fun searchRepo(query: String){
        viewModelScope.launch {
            try{
                val response = repository.searchRepo(query)
                if(response.isSuccessful) {
                    response.body()?.let {
                        _gitRepositories.postValue(Result.success(it.items))

                    } ?: run {
                        _gitRepositories.postValue(Result.failure(Exception("Empty response body")))
                    }
                } else{
                    _gitRepositories.postValue(Result.failure(Exception("Error: ${response.code()} ${response.message()}")))
                }

            }catch (e:Exception){
                _gitRepositories.postValue(Result.failure(e))
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