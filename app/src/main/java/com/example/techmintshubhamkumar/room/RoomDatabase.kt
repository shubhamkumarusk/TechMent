package com.example.techmintshubhamkumar.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.techmintshubhamkumar.room.entity.RoomGitRepo
import com.example.techmintshubhamkumar.room.entity.RoomGitUsers

@Database(entities = [RoomGitRepo::class, RoomGitUsers::class], version = 2)
@TypeConverters(Converters::class)
abstract class RoomDatabase: RoomDatabase(){
    abstract fun repoDao(): RoomGitRepoDao
    abstract fun usersDao(): RoomGitUsersDao

    companion object{
        @Volatile
        private var INSTANCE:RoomDatabase?=null
        fun getDataBase(context: Context): RoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java,
                    "roomDB"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}