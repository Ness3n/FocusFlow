package com.example.focusfflow.ui.screens.reminders

import androidx.lifecycle.ViewModel
import com.example.focusfflow.data.repository.TaskRepository
import java.text.SimpleDateFormat
import java.util.Locale

class RemindersViewModel : ViewModel() {
    // Obtenemos las tareas directamente del repositorio
    val tasks = TaskRepository.tasks

    // Acción cuando el usuario toca el Switch
    fun onToggledReminder(taskId: String, isEnabled: Boolean) {
        TaskRepository.toggleReminder(taskId, isEnabled)

        if (isEnabled) {
            // Aquí llamarías a tu lógica para PROGRAMAR la alarma en Android
            println("Alarma programada para la tarea $taskId")
        } else {
            // Aquí llamarías a tu lógica para CANCELAR la alarma
            println("Alarma cancelada para la tarea $taskId")
        }
    }

    // Función auxiliar para mostrar la hora bonita (Ej: "14:30")
    fun formatTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(timestamp)
    }
}