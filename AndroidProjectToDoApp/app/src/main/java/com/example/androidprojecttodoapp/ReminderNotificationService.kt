package com.example.androidprojecttodoapp

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class ReminderNotificationService(private val context: Context) {

    private val notificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager


    fun showNotification() {
        //var alarmManager: AlarmManager? = null

        val activityIntent = Intent(context, RemindNotificationReceiver::class.java)
        val activityPendingIntent = PendingIntent.getActivity(
            context,
            1,
            activityIntent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0
        )

        val  alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time: Long = 10000
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            activityPendingIntent
        )

        val notification = NotificationCompat.Builder(context, REMIND_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_design_services_24)
            .setContentTitle("To Do App Reminder")
            .setContentText("Do not forget about your tasks")
            .setAutoCancel(true)
            .setContentIntent(activityPendingIntent).build()
        notificationManager.notify(1, notification)


    }

    companion object {
        const val REMIND_CHANNEL_ID = "reminder_channel"
    }
}