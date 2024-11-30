package com.example.techmintshubhamkumar.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GitHubRepo (
    val id:String?,
    val name:String?,
    val owner: RepoOwner?,
    val html_url:String?,
    val description:String?,
    val contributors_url:String?,
): Parcelable