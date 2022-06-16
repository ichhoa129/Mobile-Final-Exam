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

    private val wallpapersList: MutableLiveData<List<WallpapersModel>> by lazy {
        MutableLiveData<List<WallpapersModel>>().also {
            loadWallpapersData()
        }
    }

    fun getWallpapersList(): LiveData<List<WallpapersModel>> {
        return wallpapersList
    }

    fun loadWallpapersData() {
        db = WallpaperDatabase.getInstance(application)!!
        val wallpaperDao = db!!.wallpaperDao()

        API.apiService.getWallpapers(page++, 6).enqueue(object: Callback<WallpapersList> {
            override fun onResponse(call: Call<WallpapersList>?, response: Response<WallpapersList>?) {
                if(response==null|| response.body()!!.wallpapersList ==null){
                    Log.d("DEBUG3", "CANT ACCESS API")
                    return
                }

                if(wallpapersList.value == null) {
                    wallpapersList.value = response.body()!!.wallpapersList
                    wallpapersList.value!!.forEach {
                        Log.d("DEBUG1", it._id.toString())
                        Thread {
                            if(!wallpaperDao.isRowIsExist(it._id)) {
                                val newWallpaper = Wallpaper(
                                    it._id,
                                    getByteArrayImage(it.data.large),
                                    getByteArrayImage(it.data.small)
                                )
                                wallpaperDao.insertAll(newWallpaper)
                            }
                        }.start()
                    }
                }
                else {
                    response.body()!!.wallpapersList.forEach {
                        Log.d("DEBUG1", it._id.toString())
                        wallpapersList.value = wallpapersList.value!!.plus(it)
                        Thread {
                            if(!wallpaperDao.isRowIsExist(it._id)) {
                                val newWallpaper = Wallpaper(
                                    it._id,
                                    getByteArrayImage(it.data.large),
                                    getByteArrayImage(it.data.small)
                                )
                                wallpaperDao.insertAll(newWallpaper)
                            }
                        }.start()
                    }
                }

            }

            override fun onFailure(call: Call<WallpapersList>?, t: Throwable?) {
                Log.d("DEBUG3", t.toString())
            }
        })
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