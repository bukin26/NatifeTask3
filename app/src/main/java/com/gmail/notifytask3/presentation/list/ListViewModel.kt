package com.gmail.notifytask3.presentation.list

import androidx.lifecycle.ViewModel
import com.gmail.notifytask3.repository.ListRepository

class ListViewModel(private val repository: ListRepository) : ViewModel() {

    val users = repository.users

    init {
        getUsers()
    }

    fun getUsers() {
        repository.getUsers()
    }
}