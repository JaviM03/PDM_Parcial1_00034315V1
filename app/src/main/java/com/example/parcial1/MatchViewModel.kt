package com.example.parcial1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MatchRepository
    val allMatches: LiveData<List<Match>>

    init {
        val matchesDao = MatchRoomDatabase.getDatabase(application,viewModelScope).matchDao()
        repository = MatchRepository(matchesDao)
        allMatches = repository.allMatches
    }

    fun insert(match: Match) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(match)
    }
}