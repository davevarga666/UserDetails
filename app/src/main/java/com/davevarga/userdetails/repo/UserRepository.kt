package com.davevarga.userdetails.repo

import androidx.lifecycle.LiveData
import com.davevarga.userdetails.db.UserDao
import com.davevarga.userdetails.models.User

class UserRepository(private val userDao: UserDao) {

    fun getUser(): LiveData<User> = userDao.getUser()

    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }

}