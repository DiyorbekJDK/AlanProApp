package com.example.alaproapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PageInfo(
    val resultsPerPage: Int,
    val totalResults: Int
): Parcelable