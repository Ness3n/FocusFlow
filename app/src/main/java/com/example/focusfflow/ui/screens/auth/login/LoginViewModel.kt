package com.example.focusfflow.ui.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // ESTADO: Variables que guardan lo que escribe el usuario
    var email by mutableStateOf("")
        private set // Solo el ViewModel puede cambiar el valor directamente

    var password by mutableStateOf("")
        private set

    // Variables para controlar errores o carga
    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    // EVENTOS: Funciones para actualizar el estado desde la UI
    fun onEmailChange(newEmail: String) {
        email = newEmail
        errorMessage = null // Limpiamos errores al escribir
    }

    fun onPasswordChange(newPassword: String) {
        password = newPassword
        errorMessage = null
    }

    // LÓGICA: Función que se ejecuta al dar click en "Iniciar sesión"
    fun onLoginClick(onLoginSuccess: () -> Unit) {
        // 1. Validaciones básicas
        if (email.isBlank() || password.isBlank()) {
            errorMessage = "Por favor, llena todos los campos."
            return
        }

        // 2. Simulación de inicio de sesión (Aquí conectarías con Firebase/API)
        isLoading = true

        // Simulamos un pequeño retraso de red (esto es solo demostrativo)
        // En un caso real usarías corrutinas (viewModelScope.launch)
        if (email == "user@ejemplo.com" && password == "123456") {
            isLoading = false
            onLoginSuccess() // Navegar al Home
        } else {
            isLoading = false
            errorMessage = "Correo o contraseña incorrectos."
        }
    }
}