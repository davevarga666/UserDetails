package com.davevarga.userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val user = intent.getParcelableExtra<User>("userDetails")
        textView_name.text = user?.name
        textView_phone.text = user?.phone
        textView_address.text = user?.address
        textView_city.text = user?.city
        textView_city.text = user?.zip
        textView_email.text = user?.email
        textView_birthday.text = user?.birthday

    }
}