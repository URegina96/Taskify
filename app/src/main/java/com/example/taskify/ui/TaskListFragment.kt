package com.example.taskify.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskify.R
import com.example.taskify.databinding.FragmentTaskListBinding


class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)

        binding.buttonAddTask.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }

        binding.buttonSettings.setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_settingsFragment)
        }

        return binding.root
    }
}
