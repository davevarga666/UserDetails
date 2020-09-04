package com.davevarga.userdetails.ui

import android.app.Application
import androidx.lifecycle.*
import com.davevarga.userdetails.models.User
import com.davevarga.userdetails.repo.UserRepository
import com.davevarga.userdetails.db.AppDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    val user: LiveData<User>

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        user = repository.getUser()
    }


    fun insert(user: User) {
        viewModelScope.launch {
            repository.insert(user)
        }

    }
}

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}