package com.example.ifruit.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ifruit.R

class Forgot_Password : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot__password)
    }
}