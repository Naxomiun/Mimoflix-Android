package com.nramos.mimoflix.model.recentcast

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "RecentCast")
data class RecentCast(
    @ColumnInfo(name = "id") @PrimaryKey val id: Int,
    @ColumnInfo(name = "image") val image: String?
) : Serializable