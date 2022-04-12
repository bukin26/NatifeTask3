package com.gmail.notifytask3.repository

import androidx.lifecycle.LiveData
import com.gmail.notifytask3.data.User

interface DetailsRepository {

    val user: LiveData<User>

    fun getUser(email: String)
}