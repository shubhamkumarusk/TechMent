package com.example.techmintshubhamkumar.room.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "roomGitUsers")
@Parcelize
data class RoomGitUsers (
    @PrimaryKey
    val id:String,
    val login:String?,
    val avatar_url:String?,
    val html_url:String?,

    ): Parcelable