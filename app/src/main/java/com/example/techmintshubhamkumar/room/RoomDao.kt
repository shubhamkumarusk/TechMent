package com.example.techmintshubhamkumar.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.techmintshubhamkumar.room.entity.RoomGitRepo
import com.example.techmintshubhamkumar.room.entity.RoomGitUsers


@Dao
interface RoomGitRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repos: List<RoomGitRepo>)

    @Query("SELECT * FROM roomgitrepo LIMIT 15")
    fun getAllRepos(): LiveData<List<RoomGitRepo>>

    @Delete
    suspend fun delete(repo: RoomGitRepo)
}

@Dao
interface RoomGitUsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: RoomGitUsers)

    @Query("SELECT * FROM roomGitUsers")
    fun getAllUsers(): LiveData<List<RoomGitUsers>>

    @Delete
    suspend fun delete(user: RoomGitUsers)
}