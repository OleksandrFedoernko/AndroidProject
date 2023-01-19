package com.example.androidprojecttodoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun getTasksDao(): AppTaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getData(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}