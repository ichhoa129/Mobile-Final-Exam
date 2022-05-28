package com.developerdepository.wallpaper.Common

import com.developerdepository.wallpaper.Common.Data
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @get:GET("/photos/?client_id=_fChyTeXq-vOk5v-t7GRy4Alf8lfYpXzTLPdXPeYyAo&page=1")
    val wallpapers: Call<List<WallpapersModel>>
}