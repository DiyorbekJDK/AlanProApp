package com.example.alaproapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.alaproapp.model.Default

@Parcelize
data class Thumbnails(
    val default: Default,
    val medium: Medium,
    val high: High
): Parcelable