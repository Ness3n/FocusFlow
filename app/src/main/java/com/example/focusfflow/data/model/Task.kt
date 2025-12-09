package com.example.focusfflow.data.model

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().toString(), // ID único automático
    val title: String,
    val description: String,
    val priority: String, // Ejemplo: "Alta", "Media", "Baja"
    val duration: String, // Ejemplo: "30 min"
    val isCompleted: Boolean = false
)