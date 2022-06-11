package com.developerdepository.wallpaper.Common

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.developerdepository.wallpaper.Common.WallpapersModel

class WallpapersViewModel : ViewModel() {

    private val firebaseRepository: FirebaseRepository = FirebaseRepository()
    private var page = 1

    private val wallpapersList: MutableLiveData<List<WallpapersModel>> by lazy {
        MutableLiveData<List<WallpapersModel>>().also {
            loadWallpapersData()
        }
    }

    fun getWallpapersList(): LiveData<List<WallpapersModel>> {
        return wallpapersList
    }

    fun loadWallpapersData() {
        Log.d("DEBUG4", "CALL API")
//        API.apiService.wallpapers.enqueue(object: Callback<WallpapersList> {
//            override fun onResponse(call: Call<WallpapersList>?, response: Response<WallpapersList>?) {
//                if(response==null|| response.body()!!.wallpapersList ==null){
//                    Log.d("DEBUG3", "CANT ACCESS API")
//                    return
//                }
//
//                if(wallpapersList.value == null) {
//                    wallpapersList.value = response.body()!!.wallpapersList
//                    wallpapersList.value!!.forEach {
//                        Log.d("DEBUG1", it._id.toString())
//                    }
//                }
//
//            }
//
//            override fun onFailure(call: Call<WallpapersList>?, t: Throwable?) {
//                Log.d("DEBUG3", t.toString())
//            }
//        })
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
                    }
                }
                else {
                    response.body()!!.wallpapersList.forEach {
                        Log.d("DEBUG1", it._id.toString())
                        wallpapersList.value = wallpapersList.value!!.plus(it)
                    }
                }

            }

            override fun onFailure(call: Call<WallpapersList>?, t: Throwable?) {
                Log.d("DEBUG3", t.toString())
            }
        })
    }
}