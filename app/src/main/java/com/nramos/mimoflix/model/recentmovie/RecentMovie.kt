package com.nramos.mimoflix.model.recentmovie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "RecentMovie")
data class RecentMovie(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "image") val image: String?
) : Serializable