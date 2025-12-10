package com.example.focusfflow.ui.screens.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusfflow.data.remote.Resource
import com.example.focusfflow.data.remote.api.RetrofitClient
import com.example.focusfflow.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val authRepository = AuthRepository(
        RetrofitClient.apiService,
        RetrofitClient.getTokenManager()
    )

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

        // 2. Llamar a la API real
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            when (val result = authRepository.login(email, password)) {
                is Resource.Success -> {
                    isLoading = false
                    onLoginSuccess() // Navegar al Home
                }
                is Resource.Error -> {
                    isLoading = false
                    errorMessage = result.message ?: "Error al iniciar sesión"
                }
                is Resource.Loading -> {
                    isLoading = true
                }
            }
        }
    }
}