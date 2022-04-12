package com.gmail.notifytask3.repository

import androidx.lifecycle.LiveData
import com.gmail.notifytask3.data.User

interface ListRepository {

    val users: LiveData<List<User>>

    fun getUsers()
}