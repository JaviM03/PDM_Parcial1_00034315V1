package com.example.parcial1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "match_table")
data class Match(
        @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "match") val match:String,
        @ColumnInfo(name = "Team1") val Team1:String,
        @ColumnInfo(name = "Team2") val Team2:String,
        @ColumnInfo(name = "Score1") val Score1:Int,
        @ColumnInfo(name = "Score2") val Score2:Int,
        @ColumnInfo(name = "FinalScore") val FinalScore:String

)