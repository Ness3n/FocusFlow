package com.example.focusfflow.data.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val priority: String,
    val duration: String, // El usuario ingresa minutos aquí (ej. "10")
    val isCompleted: Boolean = false,

    // NUEVOS CAMPOS PARA RECORDATORIOS
    val isReminderEnabled: Boolean = true, // Por defecto activado al crear
    val creationTime: Long = System.currentTimeMillis() // Hora exacta de creación
) {
    // Función auxiliar para saber cuándo toca la notificación
    fun getScheduledTime(): Long {
        val minutes = duration.toLongOrNull() ?: 0L
        return creationTime + (minutes * 60 * 1000) // Creación + Minutos en milisegundos
    }
}