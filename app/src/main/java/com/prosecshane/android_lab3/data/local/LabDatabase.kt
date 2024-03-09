package com.prosecshane.android_lab3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.prosecshane.android_lab3.domain.model.Feedback

@Database(
    version = DBConstants.tableVersion,
    entities = [Feedback::class],
)
abstract class LabDatabase : RoomDatabase() {
    abstract fun dao(): LabDao
}
