package com.example.taskify.ui

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
import com.example.taskify.Hilt.TaskViewModel
import com.example.taskify.R
import java.util.*

@AndroidEntryPoint
class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private val viewModel: TaskViewModel by viewModels()
    private var selectedTime: String = ""
    private var selectedDate: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        binding.editTextTaskDate.setOnClickListener {
            showDatePickerDialog()
        }

        binding.editTextTaskTime.setOnClickListener {
            showTimePickerDialog()
        }

        binding.buttonSaveTask.setOnClickListener {
            saveTask()
        }

        return binding.root
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            binding.editTextTaskDate.setText(selectedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.editTextTaskTime.setText(selectedTime)
        }, hour, minute, true)

        timePickerDialog.show()
    }

    private fun saveTask() {
        val taskName = binding.editTextTaskName.text.toString().trim()

        if (taskName.isEmpty() || selectedTime.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val task = Task(name = taskName, time = selectedTime, date = selectedDate)

        // Сохраняем задачу в базе данных через ViewModel
        viewModel.addTask(task)

        // Уведомление о успешном сохранении и переход к списку задач
        Toast.makeText(requireContext(), "Task saved successfully", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
    }
}
