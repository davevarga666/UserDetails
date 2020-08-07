package com.davevarga.userdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.davevarga.userdetails.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.*

class DetailsActivity : AppCompatActivity() {

    val db = AppDatabase.getInstance(this)
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_detail
        )
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        binding.lifecycleOwner = this
        binding.userViewModel = viewModel

        binding.userViewModel.userList = db.userDao().getUsers()



    }
}