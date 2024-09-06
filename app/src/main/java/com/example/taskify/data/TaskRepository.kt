package com.example.taskify.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
        val flow = taskDao.getAllTasks()
        return flow?.map { tasks -> tasks ?: emptyList() }?.asLiveData() ?: MutableLiveData(emptyList())
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task)
        Log.d("TaskRepository", "Task inserted: $task")
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task) // Удаляем задачу из DAO
    }
}