package com.example.androidprojecttodoapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    lateinit var taskRV: RecyclerView
    lateinit var addTask: FloatingActionButton
    lateinit var viewModal: TaskViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskRV = findViewById(R.id.RVTask)
        addTask = findViewById(R.id.addTask)
        taskRV.layoutManager=LinearLayoutManager(this)
        val taskRVAdapter=


    }
}