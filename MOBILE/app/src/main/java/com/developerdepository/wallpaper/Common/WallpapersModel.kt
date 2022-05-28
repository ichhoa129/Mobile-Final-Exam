package com.developerdepository.wallpaper.Common
import com.google.gson.annotations.SerializedName

data class WallpapersModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("urls")
    val data: Data
)