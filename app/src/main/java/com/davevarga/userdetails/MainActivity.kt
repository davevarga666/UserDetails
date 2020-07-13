package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_save = findViewById(R.id.saveButton) as Button


        btn_save.setOnClickListener {
            val myName = findViewById<TextInputEditText>(R.id.editName).text.toString()
            val myPhone = findViewById<TextInputEditText>(R.id.editPhone).text.toString()
            val myAddress = findViewById<TextInputEditText>(R.id.editAddress).text.toString()
            val myCity = findViewById<TextInputEditText>(R.id.editCity).text.toString()
            val myZip = findViewById<TextInputEditText>(R.id.editZip).text.toString()
            val myEmail = findViewById<TextInputEditText>(R.id.editEmail).text.toString()
            val myBirthday = findViewById<TextInputEditText>(R.id.editBirthday).text.toString()

            val user = User(myName, myPhone, myAddress, myCity, myZip, myEmail, myBirthday)
            val intent =
                Intent(this, DetailsActivity::class.java).apply { putExtra("userDetails", user) }
            startActivity(intent)
        }
    }


}