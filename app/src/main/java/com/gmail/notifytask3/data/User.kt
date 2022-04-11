package com.gmail.notifytask3.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val firstName: String?,
    val lastName: String?,
    val age: Int?,
    val phone: String?,
    @PrimaryKey
    val email: String,
    val image: String?
)