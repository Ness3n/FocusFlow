package com.example.focusfflow.data.repository

import com.example.focusfflow.data.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object TaskRepository {
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.update { currentList -> currentList + task }
        // AQUÍ LUEGO CONECTARÍAS CON EL SISTEMA DE NOTIFICACIONES REAL (AlarmManager)
    }

    // NUEVA FUNCIÓN: Alternar recordatorio
    fun toggleReminder(taskId: String, isEnabled: Boolean) {
        _tasks.update { currentList ->
            currentList.map { task ->
                if (task.id == taskId) {
                    task.copy(isReminderEnabled = isEnabled)
                } else {
                    task
                }
            }
        }
    }
}