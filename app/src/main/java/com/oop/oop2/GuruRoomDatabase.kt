package com.oop.oop2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.oop.oop2.DataGuru

@Database(entities = arrayOf(DataGuru::class), version = 1)
abstract class GuruRoomDatabase : RoomDatabase() {
    abstract fun guruDao(): GuruDao

    abstract val applicationContext: Context
    val db = Room.databaseBuilder(
        applicationContext,
        GuruRoomDatabase::class.java, "DBMHS"
    ).build()

}