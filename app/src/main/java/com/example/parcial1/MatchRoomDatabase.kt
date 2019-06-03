package com.example.parcial1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Match::class], version = 1, exportSchema = false)
 abstract class MatchRoomDatabase : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    companion object {
        @Volatile
        private var INSTANCE: MatchRoomDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): MatchRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MatchRoomDatabase::class.java,
                        "match_database"
                )
                        // Wipes and rebuilds instead of migrating if no Migration object.
                        // Migration is not part of this codelab.
                        .fallbackToDestructiveMigration()
                        .addCallback(MatchDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class MatchDatabaseCallback(
                private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.matchDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(matchDao: MatchDao) {


            var match = Match( "Match 1","Team 1","Team 2",0,0,"0-0" )
            matchDao.insert(match)
            match = Match( "Match 2","Team 1","Team 2",3,0,"3-0")
            matchDao.insert(match)

        }
    }
}