package com.example.focusfflow.data.remote.api

import com.example.focusfflow.data.remote.dto.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ========== HEALTH & AUTH ==========
    @GET("health")
    suspend fun healthCheck(): Response<ApiResponse<String>>

    @POST("api/auth/registro")
    suspend fun register(@Body request: RegisterRequest): Response<ApiResponse<AuthResponse>>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<ApiResponse<AuthResponse>>

    // ========== USUARIO ==========
    @GET("api/usuarios/perfil")
    suspend fun getProfile(): Response<ApiResponse<UserDto>>

    @PUT("api/usuarios/perfil")
    suspend fun updateProfile(@Body request: UpdateUserRequest): Response<ApiResponse<UserDto>>

    @DELETE("api/usuarios/perfil")
    suspend fun deleteAccount(): Response<ApiResponse<String>>

    // ========== ACTIVIDADES ==========
    @POST("api/actividades")
    suspend fun createActivity(@Body request: CreateActivityRequest): Response<ApiResponse<ActivityDto>>

    @GET("api/actividades")
    suspend fun getActivities(): Response<ApiResponse<List<ActivityDto>>>

    @GET("api/actividades/{id}")
    suspend fun getActivityById(@Path("id") id: Int): Response<ApiResponse<ActivityDto>>

    @PUT("api/actividades/{id}")
    suspend fun updateActivity(
        @Path("id") id: Int,
        @Body request: UpdateActivityRequest
    ): Response<ApiResponse<ActivityDto>>

    @PATCH("api/actividades/{id}/completar")
    suspend fun completeActivity(@Path("id") id: Int): Response<ApiResponse<ActivityDto>>

    @DELETE("api/actividades/{id}")
    suspend fun deleteActivity(@Path("id") id: Int): Response<ApiResponse<String>>

    // ========== MULTIMEDIA ==========
    @POST("api/multimedia")
    suspend fun createMultimedia(@Body request: CreateMultimediaRequest): Response<ApiResponse<MultimediaDto>>

    @GET("api/multimedia")
    suspend fun getMultimediaByActivity(@Query("actividad_id") actividadId: Int): Response<ApiResponse<List<MultimediaDto>>>

    @GET("api/multimedia/{id}")
    suspend fun getMultimediaById(@Path("id") id: Int): Response<ApiResponse<MultimediaDto>>

    @PUT("api/multimedia/{id}")
    suspend fun updateMultimedia(
        @Path("id") id: Int,
        @Body request: UpdateMultimediaRequest
    ): Response<ApiResponse<MultimediaDto>>

    @DELETE("api/multimedia/{id}")
    suspend fun deleteMultimedia(@Path("id") id: Int): Response<ApiResponse<String>>
}

