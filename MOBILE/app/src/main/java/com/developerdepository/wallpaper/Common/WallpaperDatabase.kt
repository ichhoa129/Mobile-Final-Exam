package com.developerdepository.wallpaper.Common

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Wallpaper::class], version = 1)
abstract class WallpaperDatabase : RoomDatabase() {
    abstract fun wallpaperDao(): WallpaperDao

    companion object {
        private var instance: WallpaperDatabase? = null
        fun getInstance(application: Application): WallpaperDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application,
                    WallpaperDatabase::class.java, "wallpaper_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}