package com.example.focusfflow.ui.screens.tasks.add

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusfflow.data.model.Task
import com.example.focusfflow.data.remote.Resource
import com.example.focusfflow.data.remote.api.RetrofitClient
import com.example.focusfflow.data.repository.TaskRepository
import com.example.focusfflow.notifications.ReminderScheduler
import kotlinx.coroutines.launch

class AddTasksViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository = TaskRepository(RetrofitClient.apiService)
    private val reminderScheduler = ReminderScheduler(application.applicationContext)

    // Estado del formulario
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var priority by mutableStateOf("")
        private set
    var duration by mutableStateOf("")
        private set

    // Estados de la UI
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // Eventos para actualizar el estado al escribir
    fun onTitleChange(newVal: String) {
        title = newVal
        errorMessage = null
    }
    fun onDescriptionChange(newVal: String) {
        description = newVal
        errorMessage = null
    }
    fun onPriorityChange(newVal: String) {
        priority = newVal
        errorMessage = null
    }
    fun onDurationChange(newVal: String) {
        duration = newVal
        errorMessage = null
    }

    // Guardar la tarea en la API
    fun saveTask(onSuccess: () -> Unit) {
        if (title.isBlank()) {
            errorMessage = "El título es obligatorio"
            return
        }

        val newTask = Task(
            title = title,
            description = if (description.isBlank()) "Sin descripción" else description,
            priority = if (priority.isBlank()) "Media" else priority,
            duration = if (duration.isBlank()) "0" else duration,
            fechaInicio = Task.createFechaInicio(1) // Fecha inicio: 1 minuto en el futuro
        )

        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            when (val result = taskRepository.addTask(newTask)) {
                is Resource.Success -> {
                    isLoading = false

                    // Programar el recordatorio automáticamente si la tarea tiene duración
                    result.data?.let { createdTask ->
                        val durationMinutes = createdTask.duration.toIntOrNull() ?: 0
                        if (durationMinutes > 0 && createdTask.isReminderEnabled) {
                            reminderScheduler.scheduleReminder(createdTask)
                        }
                    }

                    onSuccess() // Navegar atrás
                }
                is Resource.Error -> {
                    isLoading = false
                    errorMessage = result.message ?: "Error al guardar la tarea"
                }
                is Resource.Loading -> {
                    isLoading = true
                }
            }
        }
    }

    fun clearError() {
        errorMessage = null
    }
}