package com.davevarga.userdetails

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val name: String,
    val phone: String,
    val address: String,
    val city: String,
    val zip: String,
    val email: String,
    val birthday: String
) : Parcelable {}