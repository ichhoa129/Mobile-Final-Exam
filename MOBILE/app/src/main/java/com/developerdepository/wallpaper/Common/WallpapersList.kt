package com.developerdepository.wallpaper.Common
import com.google.gson.annotations.SerializedName

data class WallpapersList(
    @SerializedName("data")
    val wallpapersList: List<WallpapersModel>
)
