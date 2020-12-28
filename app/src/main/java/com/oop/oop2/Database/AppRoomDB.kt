package com.oop.oop2.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oop.oop2.Database.Siswa
import com.oop.oop2.Database.SiswaDao


@Database(entities = arrayOf(Siswa::class,Guru::class), version = 1)

abstract class AppRoomDB : RoomDatabase() {

    abstract fun siswaDao(): SiswaDao
   abstract fun guruDao(): GuruDao

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK){
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppRoomDB::class.java,
            "APPDB"
        ).build()

    }
}