package com.gmail.notifytask3.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UserResponse
import com.gmail.notifytask3.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(private val repository: UsersRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.fetchUsers()
            var loadedUsers = emptyList<User>()
            if (response.isSuccessful) {
                response.body()?.results?.let { it ->
                    loadedUsers = it.map(UserResponse::toUser)
                    repository.updateUsers(loadedUsers)
                }
            } else {
                loadedUsers = repository.loadUsers()
            }
            withContext(Dispatchers.Main) {
                val currentUsers = _users.value
                _users.value = currentUsers?.plus(loadedUsers) ?: loadedUsers
            }
        }
    }
}