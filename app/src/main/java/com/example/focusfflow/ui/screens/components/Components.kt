package com.example.focusfflow.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable // Importante
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip // Importante
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // Importante

// --- 1. HEADER (Reutilizable) ---
@Composable
fun AppHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF2196F3), androidx.compose.foundation.shape.CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("FF", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text("FocusFlow", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Text("Concentración y hábitos", fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// --- 2. MENU CARD (Modificada con onClick) ---
@Composable
fun MenuCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {} // <-- Nuevo parámetro
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() }, // <-- Habilitar clic
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Spacer(modifier = Modifier.height(4.dp))
            Text(subtitle, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

// --- 3. BOTTOM NAVIGATION (Con navegación real) ---
@Composable
fun BottomNavigationBar(navController: NavController? = null) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFF2196F3)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Inicio") },
            selected = false, // Podrías gestionar esto dinámicamente luego
            onClick = { navController?.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Tareas") },
            label = { Text("Tareas") },
            selected = false,
            onClick = { navController?.navigate("tasks") } // Navegar a Tareas
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Notifications, contentDescription = "Record") },
            label = { Text("Record") },
            selected = false,
            onClick = { navController?.navigate("reminders") } // Navegar a Recordatorios
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = "Ajustes") },
            label = { Text("Ajustes") },
            selected = false,
            onClick = { navController?.navigate("settings") } // Navegar a Ajustes
        )
    }
}

// --- 4. TASK ITEM (Sin cambios estructurales, solo asegúrate de tenerlo) ---
@Composable
fun TaskItem(
    title: String,
    description: String,
    status: String? = null,
    statusColor: Color = Color.Gray,
    timeInfo: String? = null,
    hasActions: Boolean = false
) {
    // ... (Tu código actual de TaskItem)
    // Si no tienes el código a mano, avísame, pero asumo que ya lo tienes del archivo original.
    // Solo estoy poniendo la estructura para no borrarlo.
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // ... Contenido visual de la tarea
        Column(modifier = Modifier.weight(1f)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(description, fontSize = 12.sp, color = Color.Gray)
            if (timeInfo != null) Text(timeInfo, fontSize = 11.sp, color = Color.LightGray)
        }
        if (status != null) {
            Text(status, color = statusColor, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

// --- 5. SETTINGS ITEM ---
@Composable
fun SettingsItem(text: String, onClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, fontSize = 16.sp, color = Color.Black)
        Icon(Icons.Default.ArrowForward, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
    }
}