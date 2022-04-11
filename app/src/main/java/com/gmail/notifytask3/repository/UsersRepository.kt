package com.gmail.notifytask3.repository

import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersService
import com.gmail.notifytask3.util.Constants

class UsersRepository(
    private val usersService: UsersService,
    private val usersDao: UsersDao
) {

    suspend fun fetchUsers(): List<User> {
        return usersService.fetchUsers().results.map { it.toUser() }
    }

    suspend fun insertUsers(users: List<User>) {
        usersDao.insertAll(users)
    }

    suspend fun loadUsers(offset: Int): List<User> {
        return usersDao.getAll(Constants.USERS_PAGE_SIZE, offset)
    }

    suspend fun deleteUsers() {
        usersDao.deleteAll()
    }

    suspend fun getUser(email: String): User {
        return usersDao.getUser(email)
    }
}