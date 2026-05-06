package com.example.sensenote.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sensenote.data.local.dao.StudentDao
import com.example.sensenote.data.local.entities.StudentEntity

@Database(entities = [StudentEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
}