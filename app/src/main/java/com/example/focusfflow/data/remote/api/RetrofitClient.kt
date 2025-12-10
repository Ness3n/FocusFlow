package com.example.focusfflow.data.remote.api

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // IMPORTANTE: Cambia esta URL por la de tu servidor
    // Para emulador Android: http://10.0.2.2:9090
    // Para dispositivo físico en misma red: http://TU_IP_LOCAL:9090
    // Para servidor remoto: https://tu-dominio.com
    private const val BASE_URL = "http://10.0.2.2:9090/"

    private var tokenManager: TokenManager? = null

    fun initialize(context: Context) {
        tokenManager = TokenManager(context)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun createOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)

        // Agregar interceptor de autenticación si TokenManager está inicializado
        tokenManager?.let {
            builder.addInterceptor(AuthInterceptor(it))
        }

        return builder.build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    fun getTokenManager(): TokenManager {
        return tokenManager ?: throw IllegalStateException(
            "RetrofitClient no está inicializado. Llama a initialize(context) primero."
        )
    }
}

