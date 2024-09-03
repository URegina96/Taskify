package com.example.taskify.Hilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskify.Task
import com.example.taskify.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel // Позволяет использовать Hilt для внедрения зависимостей в ViewModel
class TaskViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel() {
    val tasks: LiveData<List<Task>> = repository.getAllTasks() // Получаем список задач из репозитория

    // Метод для добавления задачи
    fun addTask(task: Task) {
        viewModelScope.launch {
            repository.insertTask(task) // Вставляем задачу в репозиторий
        }
    }
}