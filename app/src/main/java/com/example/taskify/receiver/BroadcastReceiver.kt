package com.example.taskify.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.WorkManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.workDataOf
import com.example.taskify.Task
import com.example.taskify.db.TaskDatabase
import com.example.taskify.worker.TaskReminderWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.*

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Intent.ACTION_BOOT_COMPLETED == intent.action) {
            CoroutineScope(Dispatchers.IO).launch {
                rescheduleAllTasks(context)
            }
        }
    }
    private suspend fun rescheduleAllTasks(context: Context) {
        val tasks = withContext(Dispatchers.IO) {
            val taskDao = TaskDatabase.getInstance(context)?.taskDao()
            val taskFlow = taskDao?.getAllTasks() ?: flowOf(emptyList())

            // Используйте collect для получения списка задач
            val taskList = taskFlow.first() // Используем first для получения списка задач
            taskList
        }

        tasks.forEach { task ->
            try {
                val triggerTime = calculateTriggerTime(task)
                if (triggerTime > System.currentTimeMillis()) {
                    scheduleTaskReminder(context, task, triggerTime)
                }
            } catch (e: Exception) {
                // Логирование ошибки
            }
        }
    }

    private fun calculateTriggerTime(task: Task): Long {
        val timeParts = task.time.split(":").map { it.toInt() }
        val dateParts = task.date.split("/").map { it.toInt() }
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, dateParts[2])
            set(Calendar.MONTH, dateParts[1] - 1) // Месяцы с 0
            set(Calendar.DAY_OF_MONTH, dateParts[0])
            set(Calendar.HOUR_OF_DAY, timeParts[0])
            set(Calendar.MINUTE, timeParts[1])
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }

        return calendar.timeInMillis
    }

    private fun scheduleTaskReminder(context: Context, task: Task, triggerTime: Long) {
        val data = workDataOf("task_name" to task.name)

        val workRequest = OneTimeWorkRequestBuilder<TaskReminderWorker>()
            .setInitialDelay(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(workRequest)
    }
}

