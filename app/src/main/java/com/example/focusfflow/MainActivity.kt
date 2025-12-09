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
import com.example.focusfflow.ui.screens.tasks.TasksScreen
import com.example.focusfflow.ui.screens.tasks.add.AddTasksScreen
import com.example.focusfflow.ui.screens.reminders.RemindersScreen
import com.example.focusfflow.ui.screens.settings.SettingsScreen
import com.example.focusfflow.ui.theme.FocusFFlowTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusFFlowTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "login") {

                        // Auth
                        composable("login") {
                            LoginScreen(
                                onLoginSuccess = {
                                    navController.navigate("home") { popUpTo("login") { inclusive = true } }
                                },
                                onRegisterClick = { navController.navigate("register") }
                            )
                        }
                        composable("register") {
                            RegisterScreen(
                                onRegisterSuccess = { navController.popBackStack() },
                                onLoginClick = { navController.popBackStack() }
                            )
                        }

                        // Main App
                        composable("home") { HomeScreen(navController) }

                        // Nuevas rutas conectadas
                        composable("tasks") { TasksScreen(navController) }
                        composable("add_task") { AddTasksScreen(navController) }
                        composable("reminders") { RemindersScreen(navController) }
                        composable("settings") { SettingsScreen(navController) }
                    }
                }
            }
        }
    }
}