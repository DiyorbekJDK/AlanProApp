package com.example.alaproapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Id(
    val kind: String,
    val videoId: String
): Parcelable