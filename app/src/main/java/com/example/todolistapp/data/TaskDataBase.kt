package com.example.todolistapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolistapp.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDataBase : RoomDatabase(){
    abstract fun taskDao(): TaskDao
}
