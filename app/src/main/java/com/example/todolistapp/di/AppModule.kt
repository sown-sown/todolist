package com.example.todolistapp.di

import android.app.Application
import androidx.room.Room
import com.example.todolistapp.data.TaskDao
import com.example.todolistapp.data.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(app: Application): TaskDataBase {
        return Room.databaseBuilder(
            app,
        TaskDataBase::class.java,
        "task_database").build()
    }

    @Provides
    fun provideTaskDao(db: TaskDataBase): TaskDao {
        return db.taskDao()
    }
}
