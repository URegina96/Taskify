package com.example.taskify.JetpackCompose

import com.example.taskify.Task

sealed class TaskState {
    object Loading : TaskState()
    data class Success(val tasks: List<Task>) : TaskState()
    data class Error(val message: String) : TaskState()
}
