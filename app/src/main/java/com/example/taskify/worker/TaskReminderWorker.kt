package com.example.taskify.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.taskify.R

class TaskReminderWorker(private val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        const val CHANNEL_ID = "task_reminder_channel"
        const val NOTIFICATION_ID = 1
    }

    override fun doWork(): Result {
        val taskName = inputData.getString("task_name") ?: return Result.failure()

        // Создание канала уведомлений для Android 8.0 и выше
        createNotificationChannel()

        // Здесь вы отправляете уведомление пользователю
        sendNotification(taskName)

        return Result.success()
    }

    private fun sendNotification(taskName: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Создаем уведомление
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon) // ваша иконка уведомления
            .setContentTitle("Напоминание")
            .setContentText("У вас есть задача: $taskName")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Удалить уведомление при нажатии
            .build()

        // Отправляем уведомление
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Task Reminder"
            val descriptionText = "Channel for task reminder notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Регистрация канала
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
