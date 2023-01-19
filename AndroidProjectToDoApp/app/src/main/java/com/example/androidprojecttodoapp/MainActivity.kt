package com.example.androidprojecttodoapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), TaskRVAdapter.TaskClickDeleteInterface,
    TaskRVAdapter.TaskClickInterface {
    lateinit var taskRV: RecyclerView
    lateinit var addFAB: FloatingActionButton
    lateinit var viewModal: TaskViewModal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        taskRV = findViewById(R.id.taskRV)
        addFAB = findViewById(R.id.idFAB)

        taskRV.layoutManager = LinearLayoutManager(this)
        val taskRVAdapter = TaskRVAdapter(this, this, this)

        taskRV.adapter = taskRVAdapter

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModal::class.java)

        viewModal.allTask.observe(
            this,
            Observer { list -> list?.let { taskRVAdapter.updateList(it) } })

        addFAB.setOnClickListener {
            val intent = Intent(this@MainActivity, AddEditTaskActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }



    override fun onTaskClick(task: Task) {
        val intent = Intent(this@MainActivity, AddEditTaskActivity::class.java)
        intent.putExtra("taskType", "Edit")
        intent.putExtra("taskTitle", task.tasksTitle)
        intent.putExtra("taskDesc", task.taskDescription)
        intent.putExtra("taskID", task.id)
        startActivity(intent)
        this.finish()
    }


    override fun onDeleteIconClick(task: Task) {
        viewModal.deleteTask(task)
        Toast.makeText(this, "${task.tasksTitle} was deleted", Toast.LENGTH_LONG).show()
    }


}