package com.example.focusfflow.ui.screens.tasks.add


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.focusfflow.data.model.Task
import com.example.focusfflow.data.repository.TaskRepository

class AddTasksViewModel : ViewModel() {
    // Estado del formulario
    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set
    var priority by mutableStateOf("")
        private set
    var duration by mutableStateOf("")
        private set

    // Eventos para actualizar el estado al escribir
    fun onTitleChange(newVal: String) { title = newVal }
    fun onDescriptionChange(newVal: String) { description = newVal }
    fun onPriorityChange(newVal: String) { priority = newVal }
    fun onDurationChange(newVal: String) { duration = newVal }

    // Guardar la tarea
    fun saveTask(onSuccess: () -> Unit) {
        if (title.isBlank()) return // Validación simple

        val newTask = Task(
            title = title,
            description = if (description.isBlank()) "Sin descripción" else description,
            priority = if (priority.isBlank()) "Media" else priority,
            duration = if (duration.isBlank()) "0" else duration
        )

        TaskRepository.addTask(newTask)
        onSuccess() // Navegar atrás
    }
}