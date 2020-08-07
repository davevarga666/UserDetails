package com.davevarga.userdetails

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : ViewModel() {

    private val repository: UserRepository
    private var userList: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        userList = repository.users
    }

    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }

    }


}