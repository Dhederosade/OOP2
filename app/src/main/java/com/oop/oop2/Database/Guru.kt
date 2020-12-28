package com.oop.oop2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guru")
data class Guru(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "nipy") val pengampu: String
)