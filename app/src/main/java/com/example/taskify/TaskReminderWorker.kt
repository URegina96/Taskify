//package com.example.taskify
//
//import android.content.Context
//import androidx.work.Worker
//import androidx.work.WorkerParameters
//import com.example.taskify.Task
//import dagger.hilt.android.qualifiers.ApplicationContext
//import javax.inject.Inject
//
//// Класс для выполнения фоновых задач, связанных с напоминанием о задачах
//class TaskReminderWorker @Inject constructor(
//    @ApplicationContext context: Context, // Внедряем контекст приложения
//    workerParams: WorkerParameters // Параметры для работы
//) : Worker(context, workerParams) {
//
//    // Основной метод, который выполняет работу в фоновом режиме
//    override fun doWork(): Result {
//        // Получаем имя задачи из входных данных
//        val taskName = inputData.getString("task_name") ?: return Result.failure()
//
//        // Возвращаем успешный результат работы
//        return Result.success()
//    }
//}