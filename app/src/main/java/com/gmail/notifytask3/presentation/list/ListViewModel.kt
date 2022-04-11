package com.gmail.notifytask3.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.repository.UsersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(private val repository: UsersRepository) : ViewModel() {

    private var firstLoading: Boolean = true
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    init {
        getUsers()
    }

    fun getUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val newUsers = try {
                val loadedUsers = repository.fetchUsers()
                if (firstLoading) {
                    repository.deleteUsers()
                    firstLoading = false
                }
                repository.insertUsers(loadedUsers)
                loadedUsers
            } catch (e: Exception) {
                repository.loadUsers()
            }
            val currentUsers = _users.value
            withContext(Dispatchers.Main) {
                _users.value = currentUsers?.plus(newUsers)?.distinct() ?: newUsers
            }
        }
    }
}