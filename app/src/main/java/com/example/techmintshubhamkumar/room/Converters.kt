package com.example.techmintshubhamkumar.room

import androidx.room.TypeConverter
import com.example.techmintshubhamkumar.room.entity.RoomGitUsers
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromRoomGitUsers(value: RoomGitUsers?): String? {
        val gson = Gson()
        val type = object : TypeToken<RoomGitUsers>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRoomGitUsers(value: String?): RoomGitUsers? {
        val gson = Gson()
        val type = object : TypeToken<RoomGitUsers>() {}.type
        return gson.fromJson(value, type)
    }
}