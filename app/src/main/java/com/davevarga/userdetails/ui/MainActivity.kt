package com.davevarga.userdetails.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.davevarga.userdetails.R
import com.davevarga.userdetails.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}