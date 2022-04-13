package com.gmail.notifytask3.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel(
    private val repository: UserRepository,
    private val email: String
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        getUser(email)
    }

    private fun getUser(email: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val newUser = repository.getUser(email)
            withContext(Dispatchers.Main) {
                _user.value = newUser
            }
        }
    }
}