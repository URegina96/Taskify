package com.example.taskify.Hilt

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskify.Task
import com.example.taskify.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel // Позволяет использовать Hilt для внедрения зависимостей в ViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    val tasks: LiveData<List<Task>> = repository.getAllTasks() // Получаем список задач из репозитория

    // Метод для добавления задачи
    fun addTask(task: Task) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertTask(task)
                Log.d("TaskViewModel", "Task added: $task")

                // Добавим логирование текущих задач после добавления
                tasks.value?.let { Log.d("TaskViewModel", "Current tasks after addition: $it") }
            }

        }
    }

    fun deleteTask(task: Task) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }
}