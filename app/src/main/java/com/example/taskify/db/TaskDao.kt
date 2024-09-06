package com.example.taskify.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.taskify.Task
import kotlinx.coroutines.flow.Flow

@Dao // Обозначает интерфейс DAO для Room
interface TaskDao {
    @Query("SELECT * FROM tasks") // SQL-запрос для получения всех задач
    fun getAllTasks(): Flow<List<Task>> // Возвращает поток задач

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Вставка задачи с заменой при конфликте
    fun insertTask(task: Task) // Вставляет задачу

    @Delete // Удаление задачи
    fun deleteTask(task: Task) // Удаляет задачу
}
