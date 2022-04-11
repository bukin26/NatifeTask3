package com.gmail.notifytask3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM users LIMIT :limit OFFSET :offset")
    suspend fun getAll(limit: Int, offset: Int): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<User>)

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String): User

    @Query("DELETE FROM users")
    suspend fun deleteAll()
}