package com.davevarga.userdetails

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.davevarga.userdetails.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelFactory: UserViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        val intent = Intent(this, DetailsActivity::class.java)



        viewModelFactory = UserViewModelFactory(application)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

        viewModel.user.observe(this, androidx.lifecycle.Observer<User> { userDetails ->
            userDetails.apply {
                binding.userInfo = userDetails
            }
        })

        editBirthday.setOnClickListener { datePick() }

//        binding.userInfo = viewModel
//        binding.setLifecycleOwner(this)
        Log.i("MainActivity", "editbirthday set")


//        clearTextFields()
        binding.saveButton.setOnClickListener {
            viewModel.insert(
                User(
                    editName.text.toString(),
                    editPhone.text.toString(),
                    editAddress.text.toString(),
                    editCity.text.toString(),
                    editZip.text.toString(),
                    editEmail.text.toString(),
                    editBirthday.text.toString()
                )
            )

            startActivity(intent)

//            CoroutineScope(Dispatchers.IO).launch {
//                val user = binding.userViewModel.userList[.value?.get(0]
//                db.userDao().insertUser(user!!)
//
//            }


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

    fun clearTextFields() {
        editName.setText("")
        editPhone.setText("")
        editAddress.setText("")
        editCity.setText("")
        editZip.setText("")
        editEmail.setText("")
        editBirthday.setText("")

    }


}




