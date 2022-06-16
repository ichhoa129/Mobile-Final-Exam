package com.developerdepository.wallpaper.Common
import com.google.gson.annotations.SerializedName

data class WallpapersModel(
    @SerializedName("_id")
    val _id: String,
    @SerializedName("urls")
    val data: Data
)