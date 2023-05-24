/*
 * Created by Emirhan KOLVER on 24.05.2023 18:23
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 24.05.2023 18:23
 */

package com.emirhankolver.usecase

import android.content.Context
import android.content.Intent
import android.os.Build
import com.emirhankolver.model.ExceptionModel

internal object IntentUseCase {

    fun getParcelableExtraSafe(intent: Intent, key: String): Throwable? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(key, ExceptionModel::class.java)?.throwable
        } else {
            intent.getParcelableExtra<ExceptionModel>(key)?.throwable
        }
    }

    fun createLaunchIntent(
        applicationContext: Context,
        keyExtra: String,
        throwable: Throwable,
        activity: Class<*>
    ): Intent {
        val intent = Intent(applicationContext, activity).also {
            it.putExtra(keyExtra, ExceptionModel(throwable))
        }
        // Clears all previous activities. So backstack will be gone
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        return intent
    }
}