package com.example.alaproapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class High(
    val height: Int,
    val url: String,
    val width: Int
): Parcelable