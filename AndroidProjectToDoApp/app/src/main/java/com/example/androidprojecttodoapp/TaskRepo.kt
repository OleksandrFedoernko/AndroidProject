package com.example.androidprojecttodoapp

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class TaskRepo(private val appTaskDao: AppTaskDao) {
    val allTask: LiveData<List<Task>> = appTaskDao.getAllTasks()

    @WorkerThread
    suspend fun insert(task: Task) {
        appTaskDao.insert(task)
    }

    @WorkerThread
    suspend fun delete(task: Task) {
        appTaskDao.delete(task)
    }

    @WorkerThread

    suspend fun update(task: Task) {
        appTaskDao.update(task)
    }
}