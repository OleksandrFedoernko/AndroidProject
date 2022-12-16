package com.example.androidprojecttodoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TViewModel (val repository: TasksRepo) : ViewModel() {

    val tasks = repository.tasks.asLiveData()

    fun update(task: Task) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }

    class WeblinkViewModelFactory(private val repository: TasksRepo) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TViewModel::class.java)) {
                return TViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }


}
