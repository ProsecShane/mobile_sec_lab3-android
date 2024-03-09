package com.prosecshane.android_lab3.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.prosecshane.android_lab3.data.local.DBConstants
import java.util.UUID

@Entity(tableName = DBConstants.tableName)
data class Feedback(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "date")
    val date: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "content")
    val content: String,
)
