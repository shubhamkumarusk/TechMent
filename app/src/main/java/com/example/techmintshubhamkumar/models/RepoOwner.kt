package com.example.techmintshubhamkumar.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RepoOwner (
    val id:String?,
    val login:String?,
    val avatar_url:String?,
    val html_url:String?,
):Parcelable
