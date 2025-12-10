package com.example.focusfflow.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusfflow.ui.screens.components.AppHeader
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import com.example.focusfflow.ui.screens.components.SettingsItem
import com.example.focusfflow.ui.screens.components.SettingsSwitchItem

@Composable
fun SettingsScreen(
    navController: NavController,
    isDarkTheme: Boolean,          // <--- Recibimos estado
    onThemeChange: (Boolean) -> Unit // <--- Recibimos acción
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Settings",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // 1. Navegar a Ajustes de Cuenta
                SettingsItem(
                    text = "Ajustes de cuenta",
                    onClick = { navController.navigate("settings_account") }
                )

                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFE0E0E0))

                // 2. Navegar a Notificaciones
                SettingsItem(
                    text = "Notificaciones",
                    onClick = { navController.navigate("settings_notifications") }
                )

                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFE0E0E0))

                // 3. Switch de Modo Oscuro (Funciona al instante)
                SettingsSwitchItem(
                    text = "Modo oscuro",
                    checked = isDarkTheme,
                    onCheckedChange = { onThemeChange(it) } // Llama a la función en MainActivity
                )

                Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color(0xFFE0E0E0))

                SettingsItem(
                    text = "Cerrar sesión",
                    onClick = {
                        navController.navigate("login") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // FAB (Opcional, si quieres mantenerlo)
        FloatingActionButton(
            onClick = { navController.navigate("add_task") },
            containerColor = Color(0xFF00C853),
            modifier = Modifier.align(Alignment.End).padding(bottom = 20.dp, end = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }

        BottomNavigationBar(navController)
    }
}