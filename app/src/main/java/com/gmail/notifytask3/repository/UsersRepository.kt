package com.gmail.notifytask3.repository

import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersList
import com.gmail.notifytask3.data.UsersService
import retrofit2.Response

class UsersRepository(private val usersService: UsersService, private val usersDao: UsersDao) {

    suspend fun fetchUsers(): Response<UsersList> {
        return usersService.fetchUsers()
    }

    suspend fun updateUsers(users: List<User>) {
        usersDao.insertAll(users)
    }

    suspend fun loadUsers(): List<User> {
        return usersDao.getAll()
    }

    suspend fun getUser(email: String): User {
        return usersDao.getUser(email)
    }
}