package com.example.taskify.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskify.Hilt.TaskViewModel
import com.example.taskify.R
import com.example.taskify.databinding.FragmentTaskListBinding
import com.example.taskify.ui.adapters.TaskAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding // Связываем элементы интерфейса
    private val viewModel: TaskViewModel by viewModels() // Внедряем ViewModel
    private lateinit var adapter: TaskAdapter // Создайте экземпляр адаптера
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(inflater, container, false) // Инфляция макета

        // Настройка адаптера
        adapter = TaskAdapter()
        binding.recyclerViewTasks.adapter = adapter

        // Установка LayoutManager для RecyclerView
        binding.recyclerViewTasks.layoutManager = LinearLayoutManager(requireContext())

        // Наблюдение за изменениями в задачах
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            Log.d("TaskListFragment", "Tasks updated: $tasks")
            adapter.submitList(tasks)
        }

        adapter.onClickDelete { task ->
            viewModel.deleteTask(task)
        }

        // Установка слушателя на кнопку для добавления задачи
        binding.buttonAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }

        return binding.root // Возвращаем корневое представление фрагмента
    }
}