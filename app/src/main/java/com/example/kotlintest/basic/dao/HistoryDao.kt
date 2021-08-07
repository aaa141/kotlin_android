package com.example.kotlintest.basic.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlintest.basic.model.History

@Dao
interface HistoryDao{
    //room에 저장된 데이터들 관리?

    @Query("SELECT * FROM history")
    fun getAll(): List<History>

    @Insert
    fun insertHistory(history: History)

    @Query("DELETE FROM history")
    fun deleteAll()

    @Delete
    fun delete(history:History)

//    // 현재 이 기능은 필요하지 않음.
//    @Query("SELECT * FROM history WHERE result LIKE :result LIMIT 1")
//    fun findByResult(result:String)
}