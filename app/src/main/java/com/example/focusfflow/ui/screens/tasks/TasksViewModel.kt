package com.example.focusfflow.ui.screens.tasks

import androidx.lifecycle.ViewModel
import com.example.focusfflow.data.repository.TaskRepository
import kotlinx.coroutines.flow.StateFlow

class TasksViewModel : ViewModel() {
    // Exponemos la lista de tareas del repositorio
    val tasks = TaskRepository.tasks
}