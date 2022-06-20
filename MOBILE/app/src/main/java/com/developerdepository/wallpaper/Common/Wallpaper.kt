package com.developerdepository.wallpaper.Common

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Wallpaper(
    @PrimaryKey val id: String,
    @ColumnInfo val urlLarge: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)  val large: ByteArray,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)  val small: ByteArray,
)
{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Wallpaper

        if (!large.contentEquals(other.large)) return false
        if (!small.contentEquals(other.small)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = large.contentHashCode()
        result = 31 * result + small.contentHashCode()
        return result
    }
}

