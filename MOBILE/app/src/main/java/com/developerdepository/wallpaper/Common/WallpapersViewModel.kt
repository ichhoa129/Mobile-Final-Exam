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

    private val wallpapersList: MutableLiveData<List<WallpapersModel>> by lazy {
        MutableLiveData<List<WallpapersModel>>().also {
            loadWallpapersData()
        }
    }

    fun getWallpapersList(): LiveData<List<WallpapersModel>> {
        return wallpapersList
    }

    fun loadWallpapersData() {
//        firebaseRepository.queryWallpapers().addOnCompleteListener {
//            if (it.isSuccessful) {
//                val result = it.result
//                if (result!!.isEmpty) {
//                    //No more results
//                } else {
//                    //Results ready to be loaded
//                    if (wallpapersList.value == null) {
//                        wallpapersList.value = result.toObjects(WallpapersModel::class.java)
//                    } else {
//                        wallpapersList.value =
//                            wallpapersList.value!!.plus(result.toObjects(WallpapersModel::class.java))
//                    }
//
//                    val lastItem: DocumentSnapshot = result.documents[result.size() - 1]
//                    firebaseRepository.lastVisible = lastItem
//                }
//            } else {
//                Log.d("VIEW_MODEL_LOG", "Error : ${it.exception!!.message}")
//            }
//        }

        API.apiService.wallpapers.enqueue(object: Callback<List<WallpapersModel>> {
            override fun onResponse(call: Call<List<WallpapersModel>>?, response: Response<List<WallpapersModel>>?) {
                if(response==null|| response.body()==null){
                    return
                }
//                wallpapersList.value = response.body()!!
                wallpapersList.value = response.body()!!
                wallpapersList.value!!.forEach {
                    Log.d("DEBUG1", it.id.toString())
                }
            }

            override fun onFailure(call: Call<List<WallpapersModel>>?, t: Throwable?) {

            }
        })
    }
}