package com.gmail.notifytask3.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

    @Insert
    suspend fun insertAll(users: List<User>)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Transaction
    suspend fun updateUsers(users: List<User>){
        deleteAll()
        insertAll(users)
    }

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String): User
}