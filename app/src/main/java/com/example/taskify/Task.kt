package com.example.taskify

import androidx.room.Entity
import androidx.room.PrimaryKey

// Сущность задачи для таблицы "tasks"
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0, // Уникальный идентификатор, генерируется автоматически
    val name: String, // Имя задачи
    val time: String, // Время, когда задача должна быть выполнена
    val date: String // Дата выполнения задачи
)