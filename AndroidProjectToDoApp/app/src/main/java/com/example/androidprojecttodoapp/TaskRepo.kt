package com.example.androidprojecttodoapp

import androidx.annotation.WorkerThread

class TasksRepo(private val tasksDao: TaskDao) {

    val tasks = tasksDao.getAllTasks()

    @WorkerThread
    suspend fun update(task: Task) {
        tasksDao.insert(task)
    }

    @WorkerThread
    suspend fun delete(task: Task) {
        tasksDao.delete(task)
    }

}