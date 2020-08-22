package com.davevarga.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.davevarga.userdetails.databinding.ActivityDetailBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        val user = intent.getParcelableExtra<User>("userDetails")
        binding.user = user
    }

}