package com.example.focusfflow.ui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.navigation.NavController
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import com.example.focusfflow.ui.screens.components.AppHeader
import com.example.focusfflow.ui.screens.components.TaskItem

@Composable
fun TasksScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Tasks",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta con tareas
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
                // Tarea 1
                TaskItem(
                    title = "Mis Tareas",
                    description = "Prioridad Media",
                    status = "Registro\nCompleto",
                    statusColor = Color(0xFF4CAF50)
                )

                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color(0xFFE0E0E0)
                )

                // Tarea 2
                TaskItem(
                    title = "Leer 25 páginas",
                    description = "Prioridad Media",
                    timeInfo = "Brevedad 30 - 40 - 45min - SL",
                    hasActions = true
                )

                Divider(
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color(0xFFE0E0E0)
                )

                // Tarea 3
                TaskItem(
                    title = "Estudiar programación",
                    description = "Alta",
                    timeInfo = "- - - 50 - 60min",
                    hasActions = true
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón flotante para ir a agregar tarea
        FloatingActionButton(
            onClick = { navController.navigate("add_task") },
            containerColor = Color(0xFF00C853),
            modifier = Modifier
                .align(Alignment.End)
                .padding(bottom = 20.dp, end = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                tint = Color.White
            )
        }

        // Barra de navegación inferior
        BottomNavigationBar(navController)
    }
}