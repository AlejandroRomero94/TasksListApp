package com.alejandro.todoapp.addtasks.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[TaskEntity::class], version=1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao
}