package com.example.alaproapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.example.alaproapp.model.Item
import com.example.alaproapp.model.PageInfo

@Parcelize
data class YoutubeModel(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo,
    val regionCode: String
) : Parcelable