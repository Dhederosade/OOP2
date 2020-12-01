package com.oop.oop2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guru")
data class DataGuru(
    @PrimaryKey val nip: Int,
    @ColumnInfo(name = "nama") val nama: String?,
    @ColumnInfo(name = "pengampu") val pengampu: String?
)
