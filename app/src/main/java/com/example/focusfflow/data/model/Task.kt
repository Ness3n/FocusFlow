package com.example.focusfflow.data.model

import com.example.focusfflow.data.remote.dto.ActivityDto
import java.text.SimpleDateFormat
import java.util.*

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val priority: String,
    val duration: String, // El usuario ingresa minutos aquí (ej. "10")
    val isCompleted: Boolean = false,

    // NUEVOS CAMPOS PARA RECORDATORIOS
    val isReminderEnabled: Boolean = true, // Por defecto activado al crear
    val creationTime: Long = System.currentTimeMillis(), // Hora exacta de creación

    // CAMPOS DE LA API
    val fechaInicio: String? = null, // formato ISO 8601
    val fechaFin: String? = null
) {
    // Función auxiliar para saber cuándo toca la notificación
    fun getScheduledTime(): Long {
        val minutes = duration.toLongOrNull() ?: 0L
        return creationTime + (minutes * 60 * 1000) // Creación + Minutos en milisegundos
    }

    companion object {
        // Convertir desde ActivityDto (API) a Task (modelo local)
        fun fromActivityDto(dto: ActivityDto): Task {
            return Task(
                id = dto.idEvento.toString(),
                title = dto.nombre,
                description = dto.descripcion,
                priority = "Media", // La API no tiene prioridad, usamos valor por defecto
                duration = dto.duracion.toString(),
                isCompleted = dto.completado,
                fechaInicio = dto.fechaInicio,
                fechaFin = dto.fechaFin,
                isReminderEnabled = dto.alarma?.activo ?: true,
                creationTime = parseIsoDate(dto.fechaInicio)
            )
        }

        private fun parseIsoDate(isoDate: String): Long {
            return try {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                format.parse(isoDate)?.time ?: System.currentTimeMillis()
            } catch (e: Exception) {
                System.currentTimeMillis()
            }
        }

        // Crear fecha ISO 8601 desde ahora + minutos
        fun createFechaInicio(minutesFromNow: Int = 0): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.MINUTE, minutesFromNow)
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            return format.format(calendar.time)
        }
    }
}