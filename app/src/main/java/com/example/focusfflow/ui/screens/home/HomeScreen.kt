package com.example.focusfflow.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.focusfflow.ui.screens.components.MenuCard

@Composable
fun HomeScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background) // <--- CLAVE: Fondo dinámico
    ) {
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Main menu",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onBackground // <--- Texto dinámico
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Hola, usuario",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant, // <--- Gris dinámico
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Cards
        Column(modifier = Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MenuCard("Mis Tareas", "Explora y gestiona", Modifier.weight(1f)) { navController?.navigate("tasks") }
                MenuCard("Modo Bloqueo", "Concentra tú atención", Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MenuCard("Recordatorios", "Recuerda tu misión", Modifier.weight(1f)) { navController?.navigate("reminders") }
                MenuCard("Configuración", "Modifica tu perfil", Modifier.weight(1f)) { navController?.navigate("settings") }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        FloatingActionButton(
            onClick = { navController?.navigate("add_task") },
            containerColor = Color(0xFF00C853),
            modifier = Modifier.align(Alignment.End).padding(bottom = 20.dp, end = 16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }

        BottomNavigationBar(navController)
    }
}