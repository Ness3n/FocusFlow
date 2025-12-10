package com.example.focusfflow.data.remote.dto

import com.google.gson.annotations.SerializedName

// ========== REQUEST DTOs ==========
data class LoginRequest(
    @SerializedName("correo")
    val correo: String,
    @SerializedName("contrasena")
    val contrasena: String
)

data class RegisterRequest(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("correo")
    val correo: String,
    @SerializedName("contrasena")
    val contrasena: String
)

data class UpdateUserRequest(
    @SerializedName("nombre")
    val nombre: String? = null,
    @SerializedName("correo")
    val correo: String? = null,
    @SerializedName("contrasena")
    val contrasena: String? = null
)

// ========== RESPONSE DTOs ==========
data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("data")
    val data: T? = null
)

data class AuthResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("usuario")
    val usuario: UserDto
)

data class UserDto(
    @SerializedName("id_usuario")
    val idUsuario: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("correo")
    val correo: String,
    @SerializedName("fecha_registro")
    val fechaRegistro: String? = null
)

