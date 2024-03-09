package com.prosecshane.android_lab3.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.prosecshane.android_lab3.domain.model.Feedback

@Dao
interface LabDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(fb: Feedback)

    @Query("SELECT * FROM ${DBConstants.tableName}")
    suspend fun get(): List<Feedback>
}
