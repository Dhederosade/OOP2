package com.oop.oop2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "siswa")
data class DataSiswa(
    @PrimaryKey val nisn: Int,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "kelas") val kelas: String?
)
