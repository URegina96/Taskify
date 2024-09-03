package com.example.taskify.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.taskify.Task
import com.example.taskify.db.TaskDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton // Обозначаем, что этот объект будет синглтоном
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    // Получаем список всех задач из DAO и преобразуем в LiveData
    fun getAllTasks(): LiveData<List<Task>> {
        return taskDao.getAllTasks()
            .map { tasks -> tasks ?: emptyList() } // Обрабатываем данные, чтобы избежать null
            .asLiveData() // Преобразуем Flow в LiveData
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task) // Вставляем задачу в DAO
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task) // Удаляем задачу из DAO
    }
}