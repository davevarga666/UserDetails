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
import java.util.Calendar.MONDAY
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, DetailsActivity::class.java)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val db = AppDatabase.getInstance(this)
        editBirthday.setOnClickListener { datePick() }


        binding.saveButton.setOnClickListener {


            val myName = editName.text.toString()
            val myPhone = editPhone.text.toString()
            val myAddress = editAddress.text.toString()
            val myCity = editCity.text.toString()
            val myZip = editZip.text.toString()
            val myEmail = editEmail.text.toString()
            val myBirthday = editBirthday.text.toString()


            val definedUser =
                User(1, myName, myPhone, myAddress, myCity, myZip, myEmail, myBirthday)

            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().insertUser(definedUser)
                startActivity(intent)
            }


        }
    }

    fun datePick() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(
                this@MainActivity, DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    // TOODO : Add the date to text view
                    editBirthday.setText("" + year + "-" + monthOfYear + "-" + dayOfMonth)
                }, year, month, day
            )
        datePickerDialog.show()
    }


}


