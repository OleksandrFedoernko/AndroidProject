package com.example.androidprojecttodoapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasksTable")
class Task(
    @ColumnInfo(name = "title")
    val tasksTitle: String,
    @ColumnInfo(name = "description")
    val taskDescription: String,
    @ColumnInfo(name = "timestamp")
    val timeStamp: String
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0;

}