package com.davevarga.userdetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM userTable")
    suspend fun getUser(): User

    @Insert
    suspend fun insertUser(user: User)


}