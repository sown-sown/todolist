package com.example.todolistapp.repository

import com.example.todolistapp.data.TaskDao
import com.example.todolistapp.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {
    fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
    }
    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }
    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}