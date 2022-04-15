package com.gmail.notifytask3.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.repository.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsViewModel @AssistedInject constructor(
    private val repository: UserRepository,
    @Assisted("email") private val email: String
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

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("email") email: String): DetailsViewModel
    }
}