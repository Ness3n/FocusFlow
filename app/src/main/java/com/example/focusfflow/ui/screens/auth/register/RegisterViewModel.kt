package com.example.focusfflow.ui.screens.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    // ESTADO
    var name by mutableStateOf("")
        private set
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // EVENTOS
    fun onNameChange(newValue: String) { name = newValue }
    fun onEmailChange(newValue: String) { email = newValue }
    fun onPasswordChange(newValue: String) { password = newValue }
    fun onConfirmPasswordChange(newValue: String) { confirmPassword = newValue }

    // LÓGICA DE REGISTRO
    fun onRegisterClick(onRegisterSuccess: () -> Unit) {
        // Validaciones
        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            errorMessage = "Todos los campos son obligatorios"
            return
        }
        if (password != confirmPassword) {
            errorMessage = "Las contraseñas no coinciden"
            return
        }
        if (password.length < 6) {
            errorMessage = "La contraseña debe tener al menos 6 caracteres"
            return
        }

        // Simulación de éxito
        isLoading = true
        errorMessage = null

        // Aquí iría la llamada a Firebase/Backend
        // Simulamos éxito inmediato:
        isLoading = false
        onRegisterSuccess()
    }
}