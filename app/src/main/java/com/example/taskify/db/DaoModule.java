package com.example.taskify.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.taskify.Task;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;
@Module // Модуль Hilt для предоставления зависимостей
@InstallIn(SingletonComponent.class) // Указывает, что зависимости будут жить в синглтоне
public class DaoModule {

    @Provides // Обозначает метод для предоставления зависимости
    @Singleton // Обозначаем, что этот объект будет синглтоном
    public TaskDao provideTaskDao(@ApplicationContext Context appContext) {
        // Возвращаем экземпляр TaskDao, например, через Room database или другой способ
        return TaskDatabase.Companion.getInstance(appContext).taskDao();
    }
}