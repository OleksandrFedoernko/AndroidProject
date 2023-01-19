package com.example.androidprojecttodoapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import java.text.SimpleDateFormat
import java.util.*

class AddEditTaskActivity : AppCompatActivity() {

    lateinit var taskEditTitle: EditText
    lateinit var taskEditDesc: EditText
    lateinit var appUpdateBtn: Button
    lateinit var returnToMainBtn: ImageButton
    lateinit var appRemindBtn: Button

    lateinit var viewModal: TaskViewModal
    var taskID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit_text)
        createNotificationChannel()

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(TaskViewModal::class.java)

        taskEditTitle = findViewById(R.id.idEdtTaskName)
        taskEditDesc = findViewById(R.id.idEdtTaskDesc)
        appUpdateBtn = findViewById(R.id.idBtn)
        appRemindBtn = findViewById(R.id.idRemindBtn)
        returnToMainBtn = findViewById(R.id.idBackBtn)

        val taskType = intent.getStringExtra("taskType")
        if (taskType.equals("Edit")) {
            val taskTitle = intent.getStringExtra("taskTitle")
            val taskDesc = intent.getStringExtra("taskDesc")
            taskID = intent.getIntExtra("taskID", -1)
            appUpdateBtn.setText("Update Task")
            taskEditTitle.setText(taskTitle)
            taskEditDesc.setText(taskDesc)
        } else {
            appUpdateBtn.setText("Save Task")
        }

        appUpdateBtn.setOnClickListener {
            val taskTitle = taskEditTitle.text.toString()
            val taskDescription = taskEditDesc.text.toString()
            if (taskType.equals("Edit")) {
                if (taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    val dateformat = SimpleDateFormat("dd MMM, yyyy, HH:mm")
                    val currentDate: String = dateformat.format(Date())
                    val updateTask = Task(taskTitle, taskDescription, currentDate)
                    updateTask.id = taskID
                    viewModal.updateTask(updateTask)
                    Toast.makeText(this, "Updated", Toast.LENGTH_LONG).show()

                }
            } else {
                if (taskTitle.isNotEmpty() && taskDescription.isNotEmpty()) {
                    val df = SimpleDateFormat("dd MMM, yyyy, HH:mm")
                    val currentDate: String = df.format(Date())
                    viewModal.addTask(Task(taskTitle, taskDescription, currentDate))
                    Toast.makeText(this, "$taskTitle Added", Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(application, MainActivity::class.java))
            this.finish()
        }
        returnToMainBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        appRemindBtn.setOnClickListener{
            val service=ReminderNotificationService(this)
            Toast.makeText(this,"Reminder set",Toast.LENGTH_LONG).show()
            service.showNotification()

        }



    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ReminderNotificationService.REMIND_CHANNEL_ID,
                "Remind",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "Used to remind user about his tasks"

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }



}