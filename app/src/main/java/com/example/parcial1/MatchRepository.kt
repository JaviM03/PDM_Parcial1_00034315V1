package com.example.parcial1

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class MatchRepository(private val MatchDao: MatchDao) {

    val allMatches: LiveData<List<Match>> = MatchDao.getAllMatches()

    @WorkerThread
    suspend fun insert(match: Match) {
        MatchDao.insert(match)
    }
}