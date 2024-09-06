package com.example.taskify.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.taskify.Task
import com.example.taskify.databinding.ItemTaskBinding

// Адаптер для работы со списком задач
class TaskAdapter : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {
    private var onClickDeleteListener: ((Task) -> Unit )? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ) // Инфляция макета элемента
        return TaskViewHolder(binding) // Возвращаем ViewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position)) // Привязываем данные задачи к элементу списка
    }

    // ViewHolder для элемента задачи
    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(task: Task) {
            binding.taskTitle.text = task.name // Устанавливаем имя задачи в соответствующее поле
            binding.taskDateTime.text =
                "${task.date} ${task.time}" // Устанавливаем дату и время задачи
            binding.deleteButton.setOnClickListener {// удаление
                onClickDeleteListener?.invoke(task)
            }
        }
    }

    fun onClickDelete(listener: (Task) -> Unit) {
        onClickDeleteListener = listener
    }

    // Класс для оптимизации списка задач
    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem.id == newItem.id // Сравнение по идентификатору
        }

        override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
            return oldItem == newItem // Сравнение содержимого задач
        }
    }
}
