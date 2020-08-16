package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.davevarga.userdetails.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object UserDetails {
        lateinit var newUser: User
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelFactory: UserViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val intent = Intent(this, DetailsActivity::class.java)

        viewModelFactory = UserViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

        editBirthday.setOnClickListener { datePick() }

        viewModel.user.observe(this, Observer { user ->
            user?.apply {
                binding.userInfo = user

            }
        })

        binding.saveButton.setOnClickListener {
            intent.putExtra("userDetails", binding.userInfo)
            startActivity(intent)

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




