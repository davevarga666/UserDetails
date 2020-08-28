package com.davevarga.userdetails

import android.app.Application
import android.content.ClipData
import androidx.lifecycle.*
import kotlinx.coroutines.delay
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