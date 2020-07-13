package com.davevarga.userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val user = intent.getParcelableExtra<User>("userDetails")
        textView_name.setText(user?.name)
        textView_phone.setText(user?.phone)
        textView_address.setText(user?.address)
        textView_city.setText(user?.city)
        textView_zip.setText(user?.zip)
        textView_email.setText(user?.email)
        textView_birthday.setText(user?.birthday)

    }
}