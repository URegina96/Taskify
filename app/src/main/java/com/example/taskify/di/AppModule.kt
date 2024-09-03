package com.example.taskify.di

import android.content.Context
import androidx.work.WorkerParameters
import com.example.taskify.TaskReminderWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module // Модуль Hilt для предоставления зависимостей
@InstallIn(SingletonComponent::class) // Указывает, что зависимости будут жить в синглтоне
object AppModule {

    @Provides // Обозначает метод для предоставления зависимости
    fun provideTaskReminderWorker(
        context: Context, // Контекст приложения
        workerParams: WorkerParameters // Параметры для рабочего задания
    ): TaskReminderWorker {
        return TaskReminderWorker(context, workerParams) // Возвращает экземпляр TaskReminderWorker
    }
}