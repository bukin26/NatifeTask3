package com.gmail.notifytask3.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val offset = _users.value?.size ?: 0
            val newUsers = repository.getUsers(offset)
            withContext(Dispatchers.Main) {
                val currentUsers = _users.value
                _users.value = currentUsers?.plus(newUsers) ?: newUsers
            }
        }
    }
}