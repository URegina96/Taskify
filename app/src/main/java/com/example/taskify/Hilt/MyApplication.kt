package com.example.taskify.Hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // Обозначает, что это приложение будет использовать Hilt для внедрения зависимостей
class MyApplication : Application()