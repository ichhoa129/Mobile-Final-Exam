package com.developerdepository.wallpaper.Common

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.net.URL
import java.nio.ByteBuffer
import com.developerdepository.wallpaper.Common.Wallpaper

class WallpapersViewModel() : ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()
    public lateinit var application : Application
    private var page = 1
    private lateinit var db : WallpaperDatabase

    private val wallpapersList: MutableLiveData<List<Wallpaper>> by lazy {
        MutableLiveData<List<Wallpaper>>().also {
            loadWallpapersData()
        }
    }

    fun getWallpapersList(): LiveData<List<Wallpaper>> {
        return wallpapersList
    }

    fun loadWallpapersData() {
        db = WallpaperDatabase.getInstance(application)!!
        val wallpaperDao = db!!.wallpaperDao()

        API.apiService.getWallpapers(page, 6).enqueue(object: Callback<WallpapersList> {
            override fun onResponse(call: Call<WallpapersList>?, response: Response<WallpapersList>?) {
                if(response?.body() == null || response.body()!!.wallpapersList == null){
                    Log.d("DEBUG3", "CANT ACCESS API")
                    Thread {
                        val newWallpapers : List<Wallpaper> = wallpaperDao.getWallpapers((page) * 6)
                        wallpapersList.postValue(newWallpapers)
                    }.start()
                    return
                }

                response?.body()!!.wallpapersList.forEach {
                    Log.d("DEBUG1", it._id.toString())

                    Thread {
                        val newWallpaper = Wallpaper(
                            it._id,
                            it.data.large,
                            getByteArrayImage(it.data.large),
                            getByteArrayImage(it.data.small)
                        )
                        if(wallpapersList.value == null) {
                            wallpapersList.postValue(listOf<Wallpaper>().plus(newWallpaper))
                        }
                        else {
                            wallpapersList.postValue(wallpapersList.value!!.plus(newWallpaper))
                        }
                        if(!wallpaperDao.isRowIsExist(it._id)) {
                            wallpaperDao.insertAll(newWallpaper)
                        }
                    }.start()
                }

            }

            override fun onFailure(call: Call<WallpapersList>?, t: Throwable?) {
                Log.d("DEBUG3", t.toString())
                Thread {
                    val newWallpapers : List<Wallpaper> = wallpaperDao.getWallpapers((page) * 6)
                    wallpapersList.postValue(newWallpapers)
                }.start()
            }


        })
        ++page
    }

    private fun getByteArrayImage(url: String): ByteArray {
        val url = URL(url)
        val output = ByteArrayOutputStream()
        val conn = url.openConnection()
        conn.setRequestProperty("User-Agent", "Firefox")

        conn.getInputStream().use { inputStream ->
            var n = 0
            val buffer = ByteArray(1024)
            while (-1 != inputStream.read(buffer).also { n = it }) {
                output.write(buffer, 0, n)
            }
        }
        val img: ByteArray = output.toByteArray()
        val imageBytes = ByteBuffer.wrap(img)

        return img
    }
}