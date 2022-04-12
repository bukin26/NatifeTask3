package com.gmail.notifytask3.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.repository.DetailsRepository

class DetailsViewModel(
    repository: DetailsRepository,
    email: String
) :
    ViewModel() {

    val user: LiveData<User> = repository.user

    init {
        repository.getUser(email)
    }
}