package com.oop.oop2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SiswaDao {
    @Query("SELECT * FROM siswa")
    fun getAll(): List<DataSiswa>

    @Query("SELECT * FROM siswa WHERE nisn IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<DataSiswa>

    @Query("SELECT * FROM siswa WHERE nama LIKE :nama AND " +
            "kelas LIKE :kelas LIMIT 1")
    fun findByName(nama: String, kelas: String): DataSiswa

    @Insert
    fun insertAll(vararg dosen: DataSiswa)

    @Delete
    fun delete(dosen: DataSiswa)
}