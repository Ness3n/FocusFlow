package com.example.focusfflow.data.remote.dto

import com.google.gson.annotations.SerializedName

// ========== REQUEST DTOs ==========
data class CreateMultimediaRequest(
    @SerializedName("url_ubicacion")
    val urlUbicacion: String,
    @SerializedName("tipo")
    val tipo: String, // "AUDIO", "VIDEO", "DOCUMENTO"
    @SerializedName("documentacion")
    val documentacion: String,
    @SerializedName("audio")
    val audio: String? = null,
    @SerializedName("actividad_id")
    val actividadId: Int
)

data class UpdateMultimediaRequest(
    @SerializedName("url_ubicacion")
    val urlUbicacion: String? = null,
    @SerializedName("tipo")
    val tipo: String? = null,
    @SerializedName("documentacion")
    val documentacion: String? = null,
    @SerializedName("audio")
    val audio: String? = null
)

// ========== RESPONSE DTOs ==========
data class MultimediaDto(
    @SerializedName("id_multimedia")
    val idMultimedia: Int,
    @SerializedName("url_ubicacion")
    val urlUbicacion: String,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("documentacion")
    val documentacion: String,
    @SerializedName("audio")
    val audio: String? = null,
    @SerializedName("id_evento")
    val idEvento: Int
)

