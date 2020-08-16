package com.davevarga.userdetails

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.davevarga.userdetails.databinding.ActivityDetailBinding
import com.davevarga.userdetails.databinding.ActivityDetailBindingImpl
import com.davevarga.userdetails.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.address
import kotlinx.android.synthetic.main.activity_detail.birthday
import kotlinx.android.synthetic.main.activity_detail.city
import kotlinx.android.synthetic.main.activity_detail.email
import kotlinx.android.synthetic.main.activity_detail.phone
import kotlinx.android.synthetic.main.activity_detail.zip
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class DetailsActivity : AppCompatActivity() {


    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var viewModelFactory: UserViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_detail
        )

//        viewModelFactory = UserViewModelFactory(application)
//        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)
        val user = getIntent().getParcelableExtra<User>("userDetails")
        //intent.extras.get("userDetails")

        binding.user = user


    }

}