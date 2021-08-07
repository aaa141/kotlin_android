package com.example.kotlintest.basic

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlintest.basic.dao.HistoryDao
import com.example.kotlintest.basic.model.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase:RoomDatabase(){
    abstract fun historyDao():HistoryDao

}