package com.example.taskify.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskify.R
import com.example.taskify.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding // Связываем элементы интерфейса

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false) // Инфляция макета

        // Установка слушателя на кнопку для перехода к списку задач
        binding.buttonStart.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_taskListFragment)
        }

        return binding.root // Возвращаем корневое представление фрагмента
    }
}
