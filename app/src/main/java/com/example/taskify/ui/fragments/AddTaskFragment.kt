package com.example.taskify.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.taskify.Task
import com.example.taskify.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.taskify.Hilt.TaskViewModel
import com.example.taskify.R
import com.example.taskify.TaskReminderWorker
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint // Позволяет использовать Hilt для внедрения зависимостей
class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding // Связываем элементы интерфейса
    private val viewModel: TaskViewModel by viewModels() // Внедряем ViewModel для работы с задачами
    private var selectedTime: String = "" // Переменная для выбранного времени
    private var selectedDate: String = "" // Переменная для выбранной даты

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false) // Инфляция макета

        // Установка слушателя на поле ввода даты
        binding.editTextTaskDate.setOnClickListener {
            showDatePickerDialog() // Показываем диалог выбора даты
        }

        // Установка слушателя на поле ввода времени
        binding.editTextTaskTime.setOnClickListener {
            showTimePickerDialog() // Показываем диалог выбора времени
        }

        // Установка слушателя на кнопку для сохранения задачи
        binding.buttonSaveTask.setOnClickListener {
            saveTask() // Сохраняем задачу
        }

        return binding.root // Возвращаем корневое представление фрагмента
    }

    // Метод для отображения диалога выбора даты
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance() // Получаем текущую дату
        val year = calendar.get(Calendar.YEAR) // Текущий год
        val month = calendar.get(Calendar.MONTH) // Текущий месяц
        val day = calendar.get(Calendar.DAY_OF_MONTH) // Текущий день

        // Создаем и показываем диалог выбора даты
        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear" // Форматируем выбранную дату
            binding.editTextTaskDate.setText(selectedDate) // Устанавливаем значение в поле
        }, year, month, day)

        datePickerDialog.show() // Показываем диалог
    }

    // Метод для отображения диалога выбора времени
    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance() // Получаем текущее время
        val hour = calendar.get(Calendar.HOUR_OF_DAY) // Текущий час
        val minute = calendar.get(Calendar.MINUTE) // Текущая минута

        // Создаем и показываем диалог выбора времени
        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute) // Форматируем выбранное время
            binding.editTextTaskTime.setText(selectedTime) // Устанавливаем значение в поле
        }, hour, minute, true)

        timePickerDialog.show() // Показываем диалог
    }

    // Метод для сохранения задачи
    private fun saveTask() {
        val taskName = binding.editTextTaskName.text.toString().trim()

        // Проверка на заполненность всех полей
        if (taskName.isEmpty() || selectedTime.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val task = Task(name = taskName, time = selectedTime, date = selectedDate)
        viewModel.addTask(task)

        scheduleTaskReminder(task)

        Toast.makeText(requireContext(), "Task saved successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_addTaskFragment_to_taskListFragment)
    }

    private fun scheduleTaskReminder(task: Task) {
        // Преобразуйте строками времени и даты в временные метки (milliseconds)
        val timeParts = task.time.split(":").map { it.toInt() }
        val dateParts = task.date.split("/").map { it.toInt() }
        val calendar = Calendar.getInstance().apply {
            set(Calendar.YEAR, dateParts[2])  // year
            set(Calendar.MONTH, dateParts[1] - 1)  // month - 1
            set(Calendar.DAY_OF_MONTH, dateParts[0])  // day

        }

        val triggerTime = calendar.timeInMillis

        // Создание данных для Worker
        val data = workDataOf("task_name" to task.name)

        // Создание задания
        val workRequest = OneTimeWorkRequestBuilder<TaskReminderWorker>()
            .setInitialDelay(triggerTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        // Запускаем Worker
        WorkManager.getInstance(requireContext()).enqueue(workRequest)
    }
}
