package com.example.androidprojecttodoapp

import androidx.lifecycle.LiveData

class TaskRepo(private val taskDao: TaskDao) {
    val allTask: LiveData<List<Task>> = taskDao.getAllTasks()
    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }
}