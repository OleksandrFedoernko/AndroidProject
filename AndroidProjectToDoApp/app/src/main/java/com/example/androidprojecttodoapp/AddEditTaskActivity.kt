package com.example.androidprojecttodoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditTaskActivity : AppCompatActivity() {
    lateinit var taskEditTitle: EditText
    lateinit var taskEditDesc: EditText
    lateinit var appUpdateBtn: Button
    lateinit var viewModal: TaskViewModal
    var taskID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_text)
        taskEditTitle = findViewById(R.id.editTitle)
        taskEditDesc = findViewById(R.id.editDescription)
        appUpdateBtn = findViewById(R.id.updateTaskBtn)
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModal::class.java)
        val taskType = intent.getStringExtra("taskType")
        if (taskType.equals("Edit")) {
            val taskTitle = intent.getStringExtra("taskTitle")
            val taskDesc = intent.getStringExtra("taskDescription")
            taskID = intent.getIntExtra("TaskID", -1)
            appUpdateBtn.setText("Update Task")
            taskEditTitle.setText(taskTitle)
            taskEditDesc.setText(taskDesc)
        } else {
            appUpdateBtn.setText("Save Text")
        }
        appUpdateBtn.setOnClickListener {
            val taskTitle = taskEditTitle.text.toString()
            val taskDesc = taskEditDesc.text.toString()
            if (taskType.equals("Edit")) {
                if (taskTitle.isNotEmpty() && taskDesc.isNotEmpty()) {
                    val dateformat = SimpleDateFormat("dd-MMM, yyyy, -HH:mm")
                    val currentDate: String = dateformat.format(Date())
                    val updateTask = Task(taskTitle, taskDesc, currentDate)
                    updateTask.id = taskID
                    viewModal.updateTask(updateTask)
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show()


                }
            }else{
                if (taskTitle.isNotEmpty()&&taskDesc.isNotEmpty()){
                    val df=SimpleDateFormat("dd-MMM, yyyy, -HH:mm")
                    val currentDate:String=df.format(Date())
                    viewModal.addTask(Task(taskTitle, taskDesc, currentDate))
                    Toast.makeText(this, "Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(application, MainActivity::class.java))
            this.finish()
        }

    }
}