/*
 * Created by Emirhan KOLVER on 24.05.2023 18:11
 * Copyright (c) 2023 . All rights reserved.
 * Last modified 24.05.2023 18:08
 */

package com.emirhankolver.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class ExceptionModel(
    val throwable: Throwable
) : Parcelable