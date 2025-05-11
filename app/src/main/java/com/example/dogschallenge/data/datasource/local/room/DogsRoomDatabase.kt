package com.example.dogschallenge.data.datasource.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DogEntity::class],
    version = DATABASE_DOGS_VERSION,
    exportSchema = false
)
abstract class DogsRoomDatabase : RoomDatabase() {

    abstract fun dogsDao(): DogsDao

    companion object {

        @Volatile
        private var instance: DogsRoomDatabase? = null

        fun getInstance(context: Context): DogsRoomDatabase =
            instance ?: synchronized(this) {
                build(context).also { instance = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context, DogsRoomDatabase::class.java, DATABASE_DOGS_NAME)
                .fallbackToDestructiveMigration(false)
                .build()
    }
}
