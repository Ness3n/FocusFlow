package com.example.focusfflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.focusfflow.ui.screens.auth.login.LoginScreen
import com.example.focusfflow.ui.screens.auth.register.RegisterScreen
import com.example.focusfflow.ui.screens.home.HomeScreen
import com.example.focusfflow.ui.screens.tasks.TasksScreen
import com.example.focusfflow.ui.screens.tasks.add.AddTasksScreen
import com.example.focusfflow.ui.screens.reminders.RemindersScreen
import com.example.focusfflow.ui.screens.settings.SettingsScreen
import com.example.focusfflow.ui.screens.settings.account.AccountSettingsScreen // Importar
import com.example.focusfflow.ui.screens.settings.notifications.NotificationSettingsScreen // Importar
import com.example.focusfflow.ui.theme.FocusFFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // 1. ESTADO GLOBAL DEL TEMA
            var isDarkTheme by remember { mutableStateOf(false) }

            // 2. Pasamos el estado al Theme
            FocusFFlowTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {
                        // ... Tus rutas anteriores (Login, Register, Home, Tasks, AddTask, Reminders) ...
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                                onRegisterClick = { navController.navigate("register") }
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = { navController.popBackStack() },
                                onLoginClick = { navController.popBackStack() }
                            )
                        }
                        composable("home") { HomeScreen(navController) }
                        composable("tasks") { TasksScreen(navController) }
                        composable("add_task") { AddTasksScreen(navController) }
                        composable("reminders") { RemindersScreen(navController) }

                        // --- RUTAS DE SETTINGS ---

                        // Ruta principal de Settings (Le pasamos el control del tema)
                        composable("settings") {
                            SettingsScreen(
                                navController = navController,
                                isDarkTheme = isDarkTheme,
                                onThemeChange = { isDarkTheme = it } // Actualizamos el estado global
                            )
                        }

                        // Sub-ruta: Ajustes de cuenta
                        composable("settings_account") {
                            AccountSettingsScreen(navController)
                        }

                        // Sub-ruta: Notificaciones
                        composable("settings_notifications") {
                            NotificationSettingsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}