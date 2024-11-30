package com.example.techmintshubhamkumar.models

data class GitHubResponse(
    val total_count: Int,
    val items: List<GitHubRepo>
)