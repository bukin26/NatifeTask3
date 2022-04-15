package com.gmail.notifytask3.repository

import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.util.Constants

interface UserRepository {

    suspend fun getUser(email: String): User

    suspend fun getUsers(offset: Int): List<User>
}

class UserRepositoryImpl(
    private val usersService: UsersService,
    private val usersDao: UsersDao
) : UserRepository {

    private var needClearCash: Boolean = true

    override suspend fun getUser(email: String): User {
        return usersDao.getUser(email)
    }

    override suspend fun getUsers(offset: Int): List<User> {
        return try {
            val loadedUsers = usersService.fetchUsers().results.map { it.toUser() }
            if (needClearCash) {
                usersDao.deleteAll()
                needClearCash = false
            }
            usersDao.insertAll(loadedUsers)
            loadedUsers
        } catch (e: Exception) {
            usersDao.getAll(Constants.USERS_PAGE_SIZE, offset)
        }
    }
}