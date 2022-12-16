package com.example.androidprojecttodoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*


@Entity(tableName = "tasks")
class Task(var title: String, var description: String) : Serializable {




    companion object {
        fun emptyTask() = Task(title = "", description = "")
    }

    override fun toString(): String {
        return "Weblink(title='$title', rating=$description)"
    }

}

