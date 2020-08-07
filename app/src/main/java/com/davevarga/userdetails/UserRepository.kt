package com.davevarga.userdetails

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val users: LiveData<List<User>> = userDao.getUsers()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

}