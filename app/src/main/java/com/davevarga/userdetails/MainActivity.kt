package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.davevarga.userdetails.databinding.ActivityMainBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var definedUser: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, DetailsActivity::class.java)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        var db=AppDatabase.getInstance(this)


        binding.saveButton.setOnClickListener {
            val myName = binding.editName.text.toString()
            val myPhone = binding.editPhone.text.toString()
            val myAddress = binding.editAddress.text.toString()
            val myCity = binding.editCity.text.toString()
            val myZip = binding.editZip.text.toString()
            val myEmail = binding.editEmail.text.toString()
            val myBirthday = binding.editBirthday.text.toString()



            definedUser = User(myName, myPhone, myAddress, myCity, myZip, myEmail, myBirthday)

            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().insertUser(definedUser)
                startActivity(intent)
            }
//            GlobalScope.launch {
//                db.userDao().insertUser(definedUser)
//                startActivity(intent)
//            }
//            db.userDao().insertUser(definedUser)


        }
//
    }


}


