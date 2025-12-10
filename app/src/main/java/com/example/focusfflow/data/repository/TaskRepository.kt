package com.example.focusfflow.data.repository

import com.example.focusfflow.data.model.Task
import com.example.focusfflow.data.remote.Resource
import com.example.focusfflow.data.remote.api.ApiService
import com.example.focusfflow.data.remote.dto.CreateActivityRequest
import com.example.focusfflow.data.remote.dto.UpdateActivityRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskRepository(private val apiService: ApiService) {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    // Obtener todas las actividades
    suspend fun fetchActivities(): Resource<List<Task>> {
        return try {
            val response = apiService.getActivities()
            if (response.isSuccessful && response.body()?.success == true) {
                val activities = response.body()?.data ?: emptyList()
                val taskList = activities.map { Task.fromActivityDto(it) }
                _tasks.value = taskList
                Resource.Success(taskList)
            } else {
                Resource.Error(response.body()?.message ?: "Error al obtener actividades")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Crear nueva actividad
    suspend fun addTask(task: Task): Resource<Task> {
        return try {
            val request = CreateActivityRequest(
                nombre = task.title,
                descripcion = task.description,
                duracion = task.duration.toIntOrNull() ?: 0,
                fechaInicio = task.fechaInicio ?: Task.createFechaInicio()
            )

            val response = apiService.createActivity(request)
            if (response.isSuccessful && response.body()?.success == true) {
                val activityDto = response.body()?.data
                if (activityDto != null) {
                    val newTask = Task.fromActivityDto(activityDto)
                    _tasks.update { currentList -> currentList + newTask }
                    Resource.Success(newTask)
                } else {
                    Resource.Error("Error al crear la actividad")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Error al crear actividad")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Actualizar actividad
    suspend fun updateTask(task: Task): Resource<Task> {
        return try {
            val taskId = task.id.toIntOrNull() ?: return Resource.Error("ID de tarea inválido")

            val request = UpdateActivityRequest(
                nombre = task.title,
                descripcion = task.description,
                duracion = task.duration.toIntOrNull(),
                fechaInicio = task.fechaInicio
            )

            val response = apiService.updateActivity(taskId, request)
            if (response.isSuccessful && response.body()?.success == true) {
                val activityDto = response.body()?.data
                if (activityDto != null) {
                    val updatedTask = Task.fromActivityDto(activityDto)
                    _tasks.update { currentList ->
                        currentList.map { if (it.id == task.id) updatedTask else it }
                    }
                    Resource.Success(updatedTask)
                } else {
                    Resource.Error("Error al actualizar la actividad")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Error al actualizar actividad")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Marcar como completada
    suspend fun completeTask(taskId: String): Resource<Task> {
        return try {
            val id = taskId.toIntOrNull() ?: return Resource.Error("ID de tarea inválido")

            val response = apiService.completeActivity(id)
            if (response.isSuccessful && response.body()?.success == true) {
                val activityDto = response.body()?.data
                if (activityDto != null) {
                    val completedTask = Task.fromActivityDto(activityDto)
                    _tasks.update { currentList ->
                        currentList.map { if (it.id == taskId) completedTask else it }
                    }
                    Resource.Success(completedTask)
                } else {
                    Resource.Error("Error al completar la actividad")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Error al completar actividad")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Eliminar actividad
    suspend fun deleteTask(taskId: String): Resource<String> {
        return try {
            val id = taskId.toIntOrNull() ?: return Resource.Error("ID de tarea inválido")

            val response = apiService.deleteActivity(id)
            if (response.isSuccessful && response.body()?.success == true) {
                _tasks.update { currentList ->
                    currentList.filter { it.id != taskId }
                }
                Resource.Success("Actividad eliminada exitosamente")
            } else {
                Resource.Error(response.body()?.message ?: "Error al eliminar actividad")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Alternar recordatorio (solo local, la API maneja alarmas automáticamente)
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