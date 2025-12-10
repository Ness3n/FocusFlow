package com.example.focusfflow.ui.screens.settings.notifications

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusfflow.ui.screens.components.AppHeader
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import com.example.focusfflow.ui.screens.components.SettingsSwitchItem // Importa el componente nuevo

@Composable
fun NotificationSettingsScreen(navController: NavController) {
    var pushEnabled by remember { mutableStateOf(true) }
    var emailEnabled by remember { mutableStateOf(false) }
    var remindersEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppHeader()
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Notificaciones",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                SettingsSwitchItem(
                    text = "Notificaciones Push",
                    checked = pushEnabled,
                    onCheckedChange = { pushEnabled = it }
                )
                Divider(color = Color(0xFFE0E0E0))

                SettingsSwitchItem(
                    text = "Correos electrónicos",
                    checked = emailEnabled,
                    onCheckedChange = { emailEnabled = it }
                )
                Divider(color = Color(0xFFE0E0E0))

                SettingsSwitchItem(
                    text = "Recordatorios de tareas",
                    checked = remindersEnabled,
                    onCheckedChange = { remindersEnabled = it }
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar(navController)
    }
}