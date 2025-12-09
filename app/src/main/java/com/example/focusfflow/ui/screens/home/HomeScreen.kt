package com.example.focusfflow.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import com.example.focusfflow.ui.screens.components.MenuCard

@Composable
fun HomeScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // ... (Header y textos de bienvenida igual que antes) ...
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).background(Color(0xFF2196F3), CircleShape),
                contentAlignment = Alignment.Center
            ) { Text("FF", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold) }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("FocusFlow", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Concentración y hábitos", fontSize = 12.sp, color = Color.Gray)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Main menu", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text("Hola, usuario", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // --- TARJETAS DEL MENÚ CON NAVEGACIÓN ---
        Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MenuCard(
                    title = "Mis Tareas",
                    subtitle = "Explora y gestiona",
                    modifier = Modifier.weight(1f),
                    onClick = { navController?.navigate("tasks") } // --> Navega a Tareas
                )
                MenuCard(
                    title = "Modo Bloqueo",
                    subtitle = "Concentra tú atención",
                    modifier = Modifier.weight(1f),
                    onClick = { /* Pendiente */ }
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MenuCard(
                    title = "Recordatorios",
                    subtitle = "Recuerda tu misión",
                    modifier = Modifier.weight(1f),
                    onClick = { navController?.navigate("reminders") } // --> Navega a Recordatorios
                )
                MenuCard(
                    title = "Configuración",
                    subtitle = "Modifica tu perfil",
                    modifier = Modifier.weight(1f),
                    onClick = { navController?.navigate("settings") } // --> Navega a Ajustes
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Actividad reciente", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 16.dp))
        // ... (Card de actividad reciente igual) ...

        Spacer(modifier = Modifier.weight(1f))

        // --- BOTÓN FLOTANTE AGREGAR ---
        FloatingActionButton(
            onClick = { navController?.navigate("add_task") }, // --> Navega a Agregar Tarea
            containerColor = Color(0xFF00C853),
            modifier = Modifier.align(Alignment.End).padding(bottom = 20.dp, end = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }

        // --- BARRA INFERIOR ---
        BottomNavigationBar(navController)
    }
}