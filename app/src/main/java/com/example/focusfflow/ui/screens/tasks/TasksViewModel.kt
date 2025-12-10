package com.example.focusfflow.ui.screens.tasks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusfflow.data.remote.Resource
import com.example.focusfflow.data.remote.api.RetrofitClient
import com.example.focusfflow.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TasksViewModel : ViewModel() {

    private val taskRepository = TaskRepository(RetrofitClient.apiService)

    // Exponemos la lista de tareas del repositorio
    val tasks = taskRepository.tasks

    // Estados de la UI
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        // Cargar tareas al iniciar
        loadTasks()
    }

    // Cargar todas las tareas desde la API
    fun loadTasks() {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            when (val result = taskRepository.fetchActivities()) {
                is Resource.Success -> {
                    isLoading = false
                }
                is Resource.Error -> {
                    isLoading = false
                    errorMessage = result.message
                }
                is Resource.Loading -> {
                    isLoading = true
                }
            }
        }
    }

    // Marcar tarea como completada
    fun completeTask(taskId: String) {
        viewModelScope.launch {
            when (val result = taskRepository.completeTask(taskId)) {
                is Resource.Success -> {
                    // Tarea completada exitosamente
                }
                is Resource.Error -> {
                    errorMessage = result.message
                }
                is Resource.Loading -> {}
            }
        }
    }

    // Eliminar tarea
    fun deleteTask(taskId: String) {
        viewModelScope.launch {
            when (val result = taskRepository.deleteTask(taskId)) {
                is Resource.Success -> {
                    // Tarea eliminada exitosamente
                }
                is Resource.Error -> {
                    errorMessage = result.message
                }
                is Resource.Loading -> {}
            }
        }
    }

    // Alternar recordatorio
    fun toggleReminder(taskId: String, isEnabled: Boolean) {
        taskRepository.toggleReminder(taskId, isEnabled)
    }

    // Limpiar mensajes de error
    fun clearError() {
        errorMessage = null
    }
}