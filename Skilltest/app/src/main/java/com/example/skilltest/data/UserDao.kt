package com.example.skilltest.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(users: List<User>)

    @Query("SELECT * FROM user_table")
    fun getAll(): Flow<List<User>>

    @Query("SELECT * FROM user_table WHERE _id = :userId")
    fun getUserById(userId: Int): Flow<User>

}