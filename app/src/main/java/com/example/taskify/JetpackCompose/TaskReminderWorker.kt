//package com.example.taskify.worker
//
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import androidx.core.app.NotificationCompat
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.example.taskify.ui.MainActivity
//import com.example.taskify.R
//
//class TaskReminderWorker(appContext: Context, workerParams: WorkerParameters) :
//    Worker(appContext, workerParams) {
//
//    override fun doWork(): Result {
//        val taskName = inputData.getString("task_name") ?: return Result.failure()
//
//        showNotification(taskName)
//        return Result.success()
//    }
//
//    private fun showNotification(taskName: String) {
//        val notificationManager =
//            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                "task_reminder",
//                "Task Reminder",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val notification = NotificationCompat.Builder(applicationContext, "task_reminder")
//            .setContentTitle("Task Reminder")
//            .setContentText("Don't forget: $taskName")
//            .setSmallIcon(R.drawable.notification_icon)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setContentIntent(getPendingIntent())
//            .build()
//
//        notificationManager.notify(taskName.hashCode(), notification)
//    }
//
//    private fun getPendingIntent(): PendingIntent {
//        val intent = Intent(applicationContext, MainActivity::class.java)
//        return PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//    }
//}
