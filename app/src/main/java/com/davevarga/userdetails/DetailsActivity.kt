package com.davevarga.userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.*

class DetailsActivity : AppCompatActivity() {

    private lateinit var displayedUser: User
    var db= AppDatabase.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        CoroutineScope(Dispatchers.Main).launch {
            displayedUser = db.userDao().getUser()
            displayedUser.apply {
                textView_name.setText(name)
                textView_phone.setText(phone)
                textView_address.setText(address)
                textView_city.setText(city)
                textView_zip.setText(zip)
                textView_email.setText(email)
                textView_birthday.setText(birthday)
            }
        }

//        GlobalScope.launch {
//            displayedUser = db.userDao().getUser()
//            displayedUser.apply {
//                textView_name.setText(name)
//                textView_phone.setText(phone)
//                textView_address.setText(address)
//                textView_city.setText(city)
//                textView_zip.setText(zip)
//                textView_email.setText(email)
//                textView_birthday.setText(birthday)
//            }
//        }








    }
}