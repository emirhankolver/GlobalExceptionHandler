/*
 * Created by Emirhan KOLVER on 24.09.2022 15:59
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 24.09.2022 15:24
 */

package com.alonew0lfxx.custom_crash_screen

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alonew0lfxx.custom_crash_screen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setOnClickListeners()
        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.bThrowError.setOnClickListener {
            throw Error("Hello, I'm the crash!")
        }
    }

}