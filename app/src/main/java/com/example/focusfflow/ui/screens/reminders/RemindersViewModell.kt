package com.example.focusfflow.ui.screens.reminders

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusfflow.data.remote.api.RetrofitClient
import com.example.focusfflow.data.repository.TaskRepository
import com.example.focusfflow.notifications.ReminderScheduler
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class RemindersViewModel(application: Application) : AndroidViewModel(application) {
    private val taskRepository = TaskRepository(RetrofitClient.apiService)
    private val reminderScheduler = ReminderScheduler(application.applicationContext)

    // Obtenemos solo las tareas con duración > 0 (las que pueden tener recordatorio)
    val tasks = taskRepository.tasks.map { taskList ->
        taskList.filter { task ->
            val duration = task.duration.toIntOrNull() ?: 0
            duration > 0
        }
    }

    init {
        // Cargar tareas al iniciar el ViewModel
        loadTasks()
    }

    // Cargar tareas desde la API
    fun loadTasks() {
        viewModelScope.launch {
            taskRepository.fetchActivities()
        }
    }

    // Acción cuando el usuario toca el Switch
    fun onToggledReminder(taskId: String, isEnabled: Boolean) {
        taskRepository.toggleReminder(taskId, isEnabled)

        // Buscar la tarea para programar/cancelar la alarma
        val task = taskRepository.tasks.value.find { it.id == taskId }

        if (task != null) {
            if (isEnabled) {
                // Programar la alarma en Android
                reminderScheduler.scheduleReminder(task)
            } else {
                // Cancelar la alarma
                reminderScheduler.cancelReminder(taskId)
            }
        }
    }

    // Función auxiliar para mostrar la hora bonita (Ej: "14:30")
    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(timestamp)
    }
}