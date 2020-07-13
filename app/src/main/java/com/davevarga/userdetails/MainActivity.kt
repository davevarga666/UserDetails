package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_save = findViewById(R.id.saveButton) as Button

        btn_save.setOnClickListener {
            val myName = findViewById<EditText>(R.id.name).text.toString()
            val myPhone = findViewById<EditText>(R.id.phone).text.toString()
            val myAddress = findViewById<EditText>(R.id.address).text.toString()
            val myCity = findViewById<EditText>(R.id.city).text.toString()
            val myZip = findViewById<EditText>(R.id.zip).text.toString()
            val myEmail = findViewById<EditText>(R.id.email).text.toString()
            val myBirthday = findViewById<EditText>(R.id.birthday).text.toString()

            val user = User(myName, myPhone, myAddress, myCity, myZip, myEmail, myBirthday)
            val intent =
                Intent(this, DetailsActivity::class.java).apply { putExtra("userDetails", user) }
            startActivity(intent)
        }
    }


}