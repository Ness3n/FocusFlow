package com.example.focusfflow.ui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.focusfflow.ui.screens.components.AppHeader
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import com.example.focusfflow.ui.screens.components.TaskItem

@Composable
fun TasksScreen(
    navController: NavController,
    viewModel: TasksViewModel = viewModel()
) {
    val taskList by viewModel.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // 1. Header Superior
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mis Tareas",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 2. CONTENEDOR PRINCIPAL (Ocupa todo el espacio restante)
        // Usamos un Box con weight(1f) para empujar la barra de menú al fondo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f) // <--- ESTO ES LA CLAVE: Ocupa todo el alto disponible
                .padding(horizontal = 16.dp)
        ) {

            if (taskList.isEmpty()) {
                // --- DISEÑO DE ESTADO VACÍO MEJORADO ---
                Column(
                    modifier = Modifier.fillMaxSize(), // Centrar en todo el espacio disponible
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Círculo de fondo para el icono
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.White, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.List,
                            contentDescription = null,
                            tint = Color(0xFF2196F3),
                            modifier = Modifier.size(48.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "No tienes tareas pendientes",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Organiza tu día agregando una nueva tarea\ncon el botón (+).",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }

            } else {
                // --- LISTA DE TAREAS ---
                Card(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        items(taskList) { task ->
                            TaskItem(
                                title = task.title,
                                description = task.description,
                                status = if (task.priority.isNotEmpty()) "Prioridad: ${task.priority}" else null,
                                statusColor = if(task.priority.equals("Alta", ignoreCase = true)) Color.Red else Color(0xFF4CAF50),
                                timeInfo = if(task.duration.isNotEmpty() && task.duration != "0") "${task.duration} min" else null,
                                hasActions = true
                            )
                            Divider(color = Color(0xFFE0E0E0), modifier = Modifier.padding(vertical = 8.dp))
                        }
                    }
                }
            }

            // 3. BOTÓN FLOTANTE (FAB)
            // Lo ponemos dentro del Box alineado abajo a la derecha
            // Así flota sobre la lista o sobre el diseño vacío
            FloatingActionButton(
                onClick = { navController.navigate("add_task") },
                containerColor = Color(0xFF00C853),
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 16.dp, end = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 4. Barra de Navegación (Siempre al final porque el Box de arriba tiene weight)
        BottomNavigationBar(navController)
    }
}