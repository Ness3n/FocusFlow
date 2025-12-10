package com.example.focusfflow.data.repository

import com.example.focusfflow.data.remote.Resource
import com.example.focusfflow.data.remote.api.ApiService
import com.example.focusfflow.data.remote.api.TokenManager
import com.example.focusfflow.data.remote.dto.*

class AuthRepository(
    private val apiService: ApiService,
    private val tokenManager: TokenManager
) {

    // Registro de usuario
    suspend fun register(nombre: String, correo: String, contrasena: String): Resource<AuthResponse> {
        return try {
            val request = RegisterRequest(nombre, correo, contrasena)
            val response = apiService.register(request)

            if (response.isSuccessful && response.body()?.success == true) {
                val authData = response.body()?.data
                if (authData != null) {
                    // Guardar token y datos de usuario
                    tokenManager.saveAuthData(
                        token = authData.token,
                        userId = authData.usuario.idUsuario,
                        name = authData.usuario.nombre,
                        email = authData.usuario.correo
                    )
                    Resource.Success(authData)
                } else {
                    Resource.Error("Error al procesar la respuesta")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Error en el registro")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Login
    suspend fun login(correo: String, contrasena: String): Resource<AuthResponse> {
        return try {
            val request = LoginRequest(correo, contrasena)
            val response = apiService.login(request)

            if (response.isSuccessful && response.body()?.success == true) {
                val authData = response.body()?.data
                if (authData != null) {
                    // Guardar token y datos de usuario
                    tokenManager.saveAuthData(
                        token = authData.token,
                        userId = authData.usuario.idUsuario,
                        name = authData.usuario.nombre,
                        email = authData.usuario.correo
                    )
                    Resource.Success(authData)
                } else {
                    Resource.Error("Error al procesar la respuesta")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Credenciales incorrectas")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Logout
    suspend fun logout() {
        tokenManager.clearAuthData()
    }

    // Obtener perfil
    suspend fun getProfile(): Resource<UserDto> {
        return try {
            val response = apiService.getProfile()

            if (response.isSuccessful && response.body()?.success == true) {
                val userData = response.body()?.data
                if (userData != null) {
                    Resource.Success(userData)
                } else {
                    Resource.Error("Error al obtener perfil")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Error al obtener perfil")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Actualizar perfil
    suspend fun updateProfile(
        nombre: String? = null,
        correo: String? = null,
        contrasena: String? = null
    ): Resource<UserDto> {
        return try {
            val request = UpdateUserRequest(nombre, correo, contrasena)
            val response = apiService.updateProfile(request)

            if (response.isSuccessful && response.body()?.success == true) {
                val userData = response.body()?.data
                if (userData != null) {
                    // Actualizar datos locales si cambió el nombre o email
                    nombre?.let {
                        tokenManager.saveAuthData(
                            token = tokenManager.token.toString(),
                            userId = userData.idUsuario,
                            name = userData.nombre,
                            email = userData.correo
                        )
                    }
                    Resource.Success(userData)
                } else {
                    Resource.Error("Error al actualizar perfil")
                }
            } else {
                Resource.Error(response.body()?.message ?: "Error al actualizar perfil")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }

    // Eliminar cuenta
    suspend fun deleteAccount(): Resource<String> {
        return try {
            val response = apiService.deleteAccount()

            if (response.isSuccessful && response.body()?.success == true) {
                tokenManager.clearAuthData()
                Resource.Success("Cuenta eliminada exitosamente")
            } else {
                Resource.Error(response.body()?.message ?: "Error al eliminar cuenta")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Error de conexión")
        }
    }
}

