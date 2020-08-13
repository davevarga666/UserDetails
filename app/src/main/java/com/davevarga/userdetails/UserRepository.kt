package com.davevarga.userdetails

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    fun getUser(): LiveData<User> = userDao.getUser()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

}