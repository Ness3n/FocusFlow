package com.example.focusfflow.ui.screens.settings


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import com.example.focusfflow.ui.screens.components.AppHeader
import com.example.focusfflow.ui.screens.components.SettingsItem

@Composable
fun SettingsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Settings",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Card de configuración
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                SettingsItem(text = "Ajustes")

                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color(0xFFE0E0E0)
                )

                SettingsItem(text = "Configuración")

                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color(0xFFE0E0E0)
                )

                SettingsItem(text = "Notificaciones")

                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color(0xFFE0E0E0)
                )

                SettingsItem(text = "Modo oscuro")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(
            onClick = {},
            containerColor = Color(0xFF00C853),
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 80.dp, end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BottomNavigationBar()
    }
}