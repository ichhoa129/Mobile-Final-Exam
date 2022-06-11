package com.developerdepository.wallpaper.Common

import com.developerdepository.wallpaper.Common.Data
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET("/api/images")
//    val wallpapers: Call<WallpapersList>
    fun getWallpapers(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Call<WallpapersList>
}