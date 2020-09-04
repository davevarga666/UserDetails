package com.davevarga.userdetails.models

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "the_user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "photoID")
    var photoID: String = "",

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "phone")
    var phone: String = "",

    @ColumnInfo(name = "address")
    var address: String = "",

    @ColumnInfo(name = "city")
    var city: String = "",

    @ColumnInfo(name = "zip")
    var zip: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "birthday")
    var birthday: String = ""
) : Parcelable


    @BindingAdapter("imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view)
                .load(url)
                .into(view)
        }

    }
