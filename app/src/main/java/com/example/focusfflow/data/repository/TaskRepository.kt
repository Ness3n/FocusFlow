package com.example.focusfflow.data.repository

import com.example.focusfflow.data.model.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object TaskRepository {
    // Usamos StateFlow para que la UI se entere automáticamente de los cambios
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.update { currentList ->
            currentList + task
        }
    }

    // Opcional: Para borrar o marcar como completada en el futuro
    fun deleteTask(taskId: String) {
        _tasks.update { currentList ->
            currentList.filter { it.id != taskId }
        }
    }
}