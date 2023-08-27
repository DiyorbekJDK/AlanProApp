package com.example.alaproapp.network

import com.example.alaproapp.model.YoutubeModel
import retrofit2.*
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("search")
    fun getAllData(
        @Query("key") key: String = "AIzaSyCu33me7ouYdN43-zQupCcmkdNa-N_Nc_8",
        @Query("channelId") channelId: String = "UC9MbH8QIc5OKUvTDxv613zQ",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResult: String = "1800",
        @Query("part") part: String = "snippet,id"
    ): Call<YoutubeModel>
}