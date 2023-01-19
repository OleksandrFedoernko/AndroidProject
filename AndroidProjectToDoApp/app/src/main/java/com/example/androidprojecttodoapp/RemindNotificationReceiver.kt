package com.example.androidprojecttodoapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class RemindNotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val service = ReminderNotificationService(context)
        service.showNotification()
    }
}