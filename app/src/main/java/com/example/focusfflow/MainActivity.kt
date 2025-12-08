package com.example.focusfflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.focusfflow.ui.screens.auth.login.LoginScreen
import com.example.focusfflow.ui.screens.auth.register.RegisterScreen
import com.example.focusfflow.ui.screens.home.HomeScreen
import com.example.focusfflow.ui.theme.FocusFFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusFFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // 1. Creamos el controlador de navegación
                    val navController = rememberNavController()

                    // 2. Definimos el "Mapa" de pantallas (NavHost)
                    NavHost(navController = navController, startDestination = "login") {

                        // RUTA: LOGIN
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    // Al loguearse, vamos al Home y borramos el login del historial
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onRegisterClick = {
                                    // Al dar clic en "Crear cuenta", vamos a registro
                                    navController.navigate("register")
                                }
                            )
                        }

                        // RUTA: REGISTRO
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = {
                                    // Al registrarse, vamos al Home
                                    navController.navigate("home") {
                                        popUpTo("login") { inclusive = true }
                                    }
                                },
                                onLoginClick = {
                                    // Si ya tiene cuenta, volvemos al login
                                    navController.popBackStack()
                                }
                            )
                        }

                        // RUTA: HOME
                        composable("home") {
                            // Pasamos el navController al Home por si necesita navegar a tareas, etc.
                            HomeScreen(navController = navController)
                        }

                        // Aquí agregarás más rutas: "tasks", "settings", etc.
                    }
                }
            }
        }
    }
}