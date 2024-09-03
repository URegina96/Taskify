package com.example.taskify.db;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taskify.Task;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
@Module // Модуль Hilt для предоставления зависимостей
@InstallIn(SingletonComponent.class) // Указывает, что зависимости будут жить в синглтоне
public class DaoModule {

    @Provides // Обозначает метод для предоставления зависимости
    @Singleton // Обозначаем, что этот объект будет синглтоном
    public TaskDao provideTaskDao() {
        // Возвращаем экземпляр TaskDao, например, через Room database или другой способ
        return new TaskDao() {
            @Nullable
            @Override
            public Object deleteTask(@NonNull Task task, @NonNull Continuation<? super Unit> $completion) {
                return null; // Заглушка для удаления задачи
            }

            @Nullable
            @Override
            public Object insertTask(@NonNull Task task, @NonNull Continuation<? super Unit> $completion) {
                return null; // Заглушка для вставки задачи
            }

            @NonNull
            @Override
            public Flow<List<Task>> getAllTasks() {
                return null; // Заглушка для получения всех задач
            }
        };
    }
}