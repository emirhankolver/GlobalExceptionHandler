/*
 * Created by Emirhan KOLVER on 24.09.2022 15:59
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 24.09.2022 15:58
 */

package com.emirhankolver.custom_crash_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.emirhankolver.custom_crash_screen.databinding.ActivityCrashBinding
import com.emirhankolver.GlobalExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@SuppressLint("SetTextI18n")

class CrashActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrashBinding.inflate(layoutInflater)
        GlobalExceptionHandler.getThrowableFromIntent(intent).let {
            Log.e(TAG, "Error Data: ", it)
        }
        setOnClickListeners()
        setContentView(binding.root)
    }

    private fun setOnClickListeners() {
        binding.bReport.setOnClickListener {
            lifecycleScope.launch {
                binding.bReport.isEnabled = false
                binding.bReport.text = "Reporting..."
                delay(2000)
                binding.bReport.text = "Reported."
                delay(1000)
                finishAffinity()
            }
        }
        binding.bRestartApp.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    companion object {
        private const val TAG = "CrashActivity"
    }

}