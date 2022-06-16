package com.developerdepository.wallpaper.Common

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WallpaperDao {
    @Query("SELECT * FROM Wallpaper")
    fun getAll(): List<Wallpaper>

    @Insert
    fun insertAll(vararg wallpaper: Wallpaper)

    @Delete
    fun delete(item: Wallpaper)

    @Query("SELECT EXISTS(SELECT * FROM Wallpaper WHERE id = :id)")
    fun isRowIsExist(id : String) : Boolean
}