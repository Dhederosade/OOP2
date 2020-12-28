package com.oop.oop2.Database

import androidx.room.*

@Dao
interface GuruDao {
    @Insert
    suspend fun addGuru(guru: Guru)

    @Update
    suspend fun updateGuru(user: Guru)

    @Delete
    suspend fun deleteGuru(user: Guru)

    @Query("SELECT * FROM guru")
    suspend fun getAllGuru(): List<Guru>

    @Query("SELECT * FROM guru WHERE id=:guru_id")
    suspend fun getGuru(guru_id: Int) : List<Guru>
}