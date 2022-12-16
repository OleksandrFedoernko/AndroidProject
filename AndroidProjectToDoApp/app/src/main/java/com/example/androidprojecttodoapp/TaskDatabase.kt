package com.example.androidprojecttodoapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase:RoomDatabase() {
    abstract fun tasksDao() :

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) : TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "tasks_database"
                ).addCallback(DatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    class DatabaseCallback(val scope: CoroutineScope) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let {
                scope.launch {
                    populateDatabase(it.weblinksDao())
                }
            }
        }

        private suspend fun populateDatabase(taskDao: TaskDao) {
            taskDao.deleteAll()
            taskDao.insert(TaskDao("",""))
            taskDao.insert(TaskDao())
        }

    }

}