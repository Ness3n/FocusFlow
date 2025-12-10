package com.example.focusfflow.data.remote.dto

import com.google.gson.annotations.SerializedName

// ========== REQUEST DTOs ==========
data class CreateActivityRequest(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("duracion")
    val duracion: Int, // en minutos
    @SerializedName("fecha_inicio")
    val fechaInicio: String // formato: "2025-12-11T09:00:00"
)

data class UpdateActivityRequest(
    @SerializedName("nombre")
    val nombre: String? = null,
    @SerializedName("descripcion")
    val descripcion: String? = null,
    @SerializedName("duracion")
    val duracion: Int? = null,
    @SerializedName("fecha_inicio")
    val fechaInicio: String? = null
)

// ========== RESPONSE DTOs ==========
data class ActivityDto(
    @SerializedName("id_evento")
    val idEvento: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("duracion")
    val duracion: Int,
    @SerializedName("fecha_inicio")
    val fechaInicio: String,
    @SerializedName("fecha_fin")
    val fechaFin: String? = null,
    @SerializedName("completado")
    val completado: Boolean = false,
    @SerializedName("id_usuario")
    val idUsuario: Int,
    @SerializedName("alarma")
    val alarma: AlarmaDto? = null
)

data class AlarmaDto(
    @SerializedName("id_alarma")
    val idAlarma: Int,
    @SerializedName("hora_alarma")
    val horaAlarma: String,
    @SerializedName("repeticion")
    val repeticion: String? = null,
    @SerializedName("sonido")
    val sonido: String? = null,
    @SerializedName("activo")
    val activo: Boolean = true
)

