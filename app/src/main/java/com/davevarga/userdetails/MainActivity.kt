package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.davevarga.userdetails.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
    }
    private val viewModelFactory: UserViewModelFactory by lazy {
        UserViewModelFactory(this.application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.editBirthday.setOnClickListener { datePick() }
        binding.saveButton.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("userDetails", binding.userInfo)
            startActivity(intent)
        }

        observerUserModel()
    }

    private fun datePick() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog =
            DatePickerDialog(
                this@MainActivity, DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    binding.editBirthday.setText("$year-$monthOfYear-$dayOfMonth")
                }, year, month, day
            )
        datePickerDialog.show()
    }

    private fun observerUserModel() {
        viewModel.user.observe(this, Observer { user ->
            binding.userInfo = user ?: User()
        })
    }


}




