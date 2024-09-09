package com.example.taskify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.taskify.R
import com.example.taskify.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Позволяет использовать Hilt для внедрения зависимостей
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // Связываем элементы интерфейса
    private lateinit var navController: NavController // Контроллер для управления навигацией

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // Инфляция макета
        setContentView(binding.root) // Установка корневого представления

        setSupportActionBar(binding.toolbar) // Устанавливаем ActionBar

        // Находим фрагмент навигации и получаем его контроллер
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
            ?: throw IllegalStateException("NavHostFragment not found")
        navController = navHostFragment.navController // Получаем контроллер

        setupActionBarWithNavController(navController) // Настраиваем ActionBar с контроллером навигации
    }

    override fun onSupportNavigateUp(): Boolean {
        // Переход вверх по навигации
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}