package com.gmail.notifytask3.repository

import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersService

class UsersRepository(private val usersService: UsersService, private val usersDao: UsersDao) {

    suspend fun fetchUsers(): List<User> {
        return usersService.fetchUsers().results.map { it.toUser() }
    }

    suspend fun insertUsers(users: List<User>) {
        usersDao.insertAll(users)
    }

    suspend fun loadUsers(): List<User> {
        return usersDao.getAll()
    }

    suspend fun deleteUsers() {
        usersDao.deleteAll()
    }

    suspend fun getUser(email: String): User {
        return usersDao.getUser(email)
    }
}