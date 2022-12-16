package com.example.androidprojecttodoapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), TaskRVAdapter.TaskClickDeleteInterface,
    TaskRVAdapter.TaskClickInterface {
    lateinit var taskRV: RecyclerView
    lateinit var addTask: FloatingActionButton
    lateinit var viewModal: TaskViewModal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskRV = findViewById(R.id.RVTask)
        addTask = findViewById(R.id.addTask)
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

        addTask.setOnClickListener{
            val intent=Intent(this@MainActivity,AddEditTaskActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(task: Task) {
      viewModal.deleteTask(task)
        Toast.makeText(this, "${task.tasksTitle} Delete", Toast.LENGTH_LONG).show()
    }

    override fun onTaskClick(task: Task) {
        val intent=Intent(this@MainActivity,AddEditTaskActivity::class.java)
        intent.putExtra("TaskType","Edit")
        intent.putExtra("TaskTitle",task.tasksTitle)
        intent.putExtra("TaskDescription",task.description)
        intent.putExtra("TaskID",task.id)
        startActivity(intent)
        this.finish()

    }


}