package com.example.androidprojecttodoapp

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
class TaskDao {

   @Query("SELECT * FROM tasks")
   fun getAllTasks():Flow<List<Task>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weblink: Task)

    @Delete
    suspend fun delete(weblink: Task)

    @Query("DELETE FROM tasks WHERE title=:title")
    suspend fun delete(title: String)

    @Query("DELETE FROM tasks")
    suspend fun deleteAll()
}
