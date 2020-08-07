package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
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
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, DetailsActivity::class.java)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.setLifecycleOwner(this)
        binding.userViewModel = viewModel


        val db = AppDatabase.getInstance(this)
        editBirthday.setOnClickListener { datePick() }


        binding.saveButton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val user = binding.userViewModel.userList[.value?.get(0]
                db.userDao().insertUser(user!!)
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
                    editBirthday.setText("$year-$monthOfYear-$dayOfMonth")
                }, year, month, day
            )
        datePickerDialog.show()
    }


}


