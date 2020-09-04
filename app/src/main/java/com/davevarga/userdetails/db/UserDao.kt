package com.davevarga.userdetails.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.davevarga.userdetails.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM the_user_table")
    fun getUser(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

}