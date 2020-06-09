package com.nramos.mimoflix.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nramos.mimoflix.persistance.AppRoomDataBase.Companion.DB_VERSION

@Database(entities = [MovieDB::class], version = DB_VERSION, exportSchema = false)
abstract class AppRoomDataBase : RoomDatabase() {

    abstract fun getFavoriteDao(): FavoriteDao

    companion object {

        const val DB_VERSION = 1
        private const val DB_NAME = "MimoflixV1.db"

        @Volatile
        private var INSTANCE: AppRoomDataBase? = null

        fun getInstance(context: Context) : AppRoomDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: build(context).also {
                INSTANCE = it
            }
        }

        private fun build(context: Context) = Room.databaseBuilder(context.applicationContext, AppRoomDataBase::class.java, DB_NAME).build()

    }

}