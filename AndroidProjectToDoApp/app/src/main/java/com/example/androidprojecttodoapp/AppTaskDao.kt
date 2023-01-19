package com.example.androidprojecttodoapp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AppTaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(task: Task)

    @Update
     fun update(task: Task)

    @Delete
     fun delete(task: Task)

    @Query("Select * from tasksTable order by id ASC")
    fun getAllTasks(): LiveData<List<Task>>
}