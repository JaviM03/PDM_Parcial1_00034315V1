package com.example.parcial1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MatchDao {

    @Query("SELECT * from match_table")
    fun getAllMatches(): LiveData<List<Match>>

    @Insert
    suspend fun insert(word: Match)

    @Query("DELETE FROM match_table")
    fun deleteAll()
}