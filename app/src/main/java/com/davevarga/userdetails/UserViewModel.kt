package com.davevarga.userdetails

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val repository: UserRepository
    val user: LiveData<User>

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        //ITT LEHET A KUTYA ELASVA
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
