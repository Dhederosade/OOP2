package com.oop.oop2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.oop.oop2.DataGuru

@Dao
interface GuruDao {
    @Query("SELECT * FROM guru")
    fun getAll(): List<DataSiswa>

    @Query("SELECT * FROM guru WHERE nip IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<DataSiswa>

    @Query("SELECT * FROM guru WHERE nama LIKE :nama AND " +
            "pengampu LIKE :pengampu LIMIT 1")
    fun findByName(nama: String, pengampu: String): DataGuru

    @Insert
    fun insertAll(vararg guru: DataGuru)

    @Delete
    fun delete(dosen: DataGuru)
}