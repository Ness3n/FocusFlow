package com.example.focusfflow


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.focusfflow.ui.screens.auth.login.LoginScreen
import com.example.focusfflow.ui.screens.auth.register.RegisterScreen
import com.example.focusfflow.ui.screens.home.HomeScreen
import com.example.focusfflow.ui.screens.reminders.RemindersScreen
import com.example.focusfflow.ui.screens.settings.SettingsScreen
import com.example.focusfflow.ui.screens.tasks.TasksScreen
import com.example.focusfflow.ui.theme.FocusFFlowTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FocusFFlowTheme  {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Aquí puedes cambiar la pantalla que quieres mostrar
                    LoginScreen()


                    // Otras pantallas disponibles:
                    // LoginScreen()
                    // HomeScreen()
                    // TasksScreen()
                    // AddTasksScreen()
                    // RemindersScreen()
                    // SettingsScreen()
                }
            }
        }
    }
}