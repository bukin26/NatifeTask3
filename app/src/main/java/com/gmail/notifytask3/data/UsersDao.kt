package com.gmail.notifytask3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String): User
}