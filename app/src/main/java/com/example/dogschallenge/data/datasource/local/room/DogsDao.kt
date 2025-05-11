package com.example.dogschallenge.data.datasource.local.room

import androidx.annotation.VisibleForTesting
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface DogsDao {

    @Transaction
    suspend fun insertAll(dogs: List<DogEntity>) {
        delete()
        insert(dogs)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dogs: List<DogEntity>)

    @Query("SELECT * FROM $TABLE_DOG")
    suspend fun getAll(): List<DogEntity>

    @VisibleForTesting
    @Query("DELETE FROM $TABLE_DOG")
    suspend fun delete(): Int
}
