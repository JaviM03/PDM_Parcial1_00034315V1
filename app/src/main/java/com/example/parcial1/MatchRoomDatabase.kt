package com.example.parcial1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Match::class), version = 1, exportSchema = false)
 abstract class MatchRoomDatabase : RoomDatabase() {

    abstract fun MatchDao(): MatchDao

    companion object {
        @Volatile
        private var INSTANCE: MatchRoomDatabase? = null

        fun getDatabase(context: Context): MatchRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MatchRoomDatabase::class.java,
                    "Match_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}