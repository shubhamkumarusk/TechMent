package com.example.techmintshubhamkumar.room.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "roomgitrepo")
@Parcelize
data class RoomGitRepo(
    @PrimaryKey
    val id:String,
    val name:String?,
    val owner: RoomGitUsers?,
    val html_url:String?,
    val description:String?,
    val contributors_url:String?,
):Parcelable