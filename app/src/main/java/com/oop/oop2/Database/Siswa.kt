package com.oop.oop2.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "siswa")
data class Siswa(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nama") val merk: String,
    @ColumnInfo(name = "nis") val stok: Int,
    @ColumnInfo(name = "kelas") val harga: Int
)