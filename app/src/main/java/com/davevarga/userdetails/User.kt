package com.davevarga.userdetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "userTable")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "address")
    val address: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "zip")
    val zip: String,
    @ColumnInfo(name = "email")
    val email: String,
    @ColumnInfo(name = "birthday")
    val birthday: String
)