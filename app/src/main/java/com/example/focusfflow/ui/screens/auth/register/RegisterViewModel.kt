package com.example.focusfflow.ui.screens.auth.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.focusfflow.data.remote.Resource
import com.example.focusfflow.data.remote.api.RetrofitClient
import com.example.focusfflow.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val authRepository = AuthRepository(
        RetrofitClient.apiService,
        RetrofitClient.getTokenManager()
    )

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
    fun onNameChange(newValue: String) {
        name = newValue
        errorMessage = null
    }
    fun onEmailChange(newValue: String) {
        email = newValue
        errorMessage = null
    }
    fun onPasswordChange(newValue: String) {
        password = newValue
        errorMessage = null
    }
    fun onConfirmPasswordChange(newValue: String) {
        confirmPassword = newValue
        errorMessage = null
    }

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

        // Llamar a la API real
        isLoading = true
        errorMessage = null

        viewModelScope.launch {
            when (val result = authRepository.register(name, email, password)) {
                is Resource.Success -> {
                    isLoading = false
                    onRegisterSuccess() // Navegar al Home
                }
                is Resource.Error -> {
                    isLoading = false
                    errorMessage = result.message ?: "Error al registrarse"
                }
                is Resource.Loading -> {
                    isLoading = true
                }
            }
        }
    }
}