/*
 * Created by Emirhan KOLVER on 24.09.2022 18:37
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 24.09.2022 18:37
 */
package com.alonew0lfxx.custom_crash_screen

import android.app.Application
import com.alonew0lfxx.GlobalExceptionHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this,CrashActivity::class.java)
    }

}