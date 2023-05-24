/*
 * Created by Emirhan KOLVER on 24.09.2022 15:59
 * Copyright (c) 2022 . All rights reserved.
 * Last modified 24.09.2022 15:59
 */

package com.emirhankolver

import android.content.Context
import android.content.Intent
import com.emirhankolver.usecase.IntentUseCase
import kotlin.system.exitProcess

/**
 * [applicationContext]: Required for launching the activity.
 *
 * [defaultHandler]:
 * If there any kind of error occurs while launching the [activityToBeLaunched]
 * default [Thread.UncaughtExceptionHandler] will be triggered. As a result
 * Android's default Crash Dialog will be show up.
 *
 * [activityToBeLaunched]: The activity to be launched :D,
 * it will put Exception data as JSON string. It can be
 * easily converted back to [Throwable] class by ```Gson().fromJSON(str,Throwable::class.java)```
 * function.
 */
class GlobalExceptionHandler private constructor(
    private val applicationContext: Context,
    private val defaultHandler: Thread.UncaughtExceptionHandler,
    private val activityToBeLaunched: Class<*>
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        try {
            launchActivity(applicationContext, activityToBeLaunched, p1)
            exitProcess(0)
        } catch (e: Exception) {
            defaultHandler.uncaughtException(p0, p1)
        }
    }

    private fun launchActivity(
        applicationContext: Context,
        activity: Class<*>,
        exception: Throwable
    ) {
        applicationContext.startActivity(
            IntentUseCase.createLaunchIntent(
                applicationContext = applicationContext,
                keyExtra = EXTRA_CRASH_DATA,
                throwable = exception,
                activity = activity
            )
        )
    }

    companion object {
        private const val EXTRA_CRASH_DATA = "CrashData"

        /**
         * [applicationContext]: Required for launching the activity.
         *
         * [activityToBeLaunched]: The activity that to be launched :D,
         * it will put Exception data as JSON string. It can be
         * easily converted back to [Throwable] with [getThrowableFromIntent].
         *
         * **Example usage at activity:**
         * ```
         * GlobalExceptionHandler.getThrowableFromIntent(intent).let {
         *   Log.e(TAG, "Error Data: ", it)
         *   // Send logs or do your stuff...
         * }
         * ```
         */
        fun initialize(
            applicationContext: Context,
            activityToBeLaunched: Class<*>
        ) {
            val handler = GlobalExceptionHandler(
                applicationContext,
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
                activityToBeLaunched
            )
            Thread.setDefaultUncaughtExceptionHandler(handler)
        }

        /**
         * Gets throwable data from activity's intent. It'll return null if stringExtra has not been
         * found or another reasons.
         */
        fun getThrowableFromIntent(intent: Intent): Throwable? {
            return IntentUseCase.getParcelableExtraSafe(intent, EXTRA_CRASH_DATA)
        }
    }
}