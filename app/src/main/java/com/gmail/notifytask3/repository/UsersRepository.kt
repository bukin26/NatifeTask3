package com.gmail.notifytask3.repository

import com.gmail.notifytask3.data.User
import com.gmail.notifytask3.data.UsersDao
import com.gmail.notifytask3.data.UsersService

class UsersRepository(private val usersService: UsersService, private val usersDao: UsersDao) {

    suspend fun fetchUsers() = usersService.fetchUsers()

    suspend fun updateUsers(users: List<User>) = usersDao.updateUsers(users)

    suspend fun loadUsers() = usersDao.getAll()

    suspend fun getUser(id: String): User = usersDao.getUser(id)
}