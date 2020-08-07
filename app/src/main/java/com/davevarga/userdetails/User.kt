package com.davevarga.userdetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "phone")
    var phone: String,

    @ColumnInfo(name = "address")
    var address: String,

    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "zip")
    var zip: String,

    @ColumnInfo(name = "email")
    var email: String,

    @ColumnInfo(name = "birthday")
    var birthday: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}