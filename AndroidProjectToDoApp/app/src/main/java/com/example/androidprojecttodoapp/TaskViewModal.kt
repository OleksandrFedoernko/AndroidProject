package com.example.androidprojecttodoapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModal(application: Application) : AndroidViewModel(application) {
    val allTask: LiveData<List<Task>>
    val repo: TaskRepo

    init {
        val dao = TaskDatabase.getData(application).getTasksDao()
        repo = TaskRepo(dao)
        allTask = repo.allTask
    }

    fun deleteTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(task)
    }

    fun addTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(task)
    }
}