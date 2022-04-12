package com.gmail.notifytask3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.util.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RepositoryImpl private constructor(
    private val usersService: UsersService,
    private val usersDao: UsersDao
) : DetailsRepository, ListRepository {

    companion object {

        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(usersService: UsersService, usersDao: UsersDao): RepositoryImpl {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryImpl(usersService, usersDao).also { INSTANCE = it }
            }
        }
    }

    private var firstLoading: Boolean = true
    private val _user = MutableLiveData<User>()
    override val user: LiveData<User>
        get() = _user
    private val _users = MutableLiveData<List<User>>()
    override val users: LiveData<List<User>>
        get() = _users

    override fun getUser(email: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val newUser = usersDao.getUser(email)
            withContext(Dispatchers.Main) {
                _user.value = newUser
            }
        }
    }

    override fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val newUsers = try {
                val loadedUsers = usersService.fetchUsers().results.map { it.toUser() }
                if (firstLoading) {
                    usersDao.deleteAll()
                    firstLoading = false
                }
                usersDao.insertAll(loadedUsers)
                loadedUsers
            } catch (e: Exception) {
                val offset = _users.value?.size ?: 0
                usersDao.getAll(Constants.USERS_PAGE_SIZE, offset)
            }
            val currentUsers = _users.value
            withContext(Dispatchers.Main) {
                _users.value = currentUsers?.plus(newUsers) ?: newUsers
            }
        }
    }
}