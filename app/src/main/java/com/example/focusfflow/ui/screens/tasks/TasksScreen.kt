package com.example.focusfflow.ui.screens.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
    viewModel: TasksViewModel = viewModel() // Inyectamos el ViewModel
) {
    // Observamos la lista de tareas. Cuando cambie el repositorio, esto se actualiza solo.
    val taskList by viewModel.tasks.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
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

        // Si la lista está vacía, mostramos un mensaje
        if (taskList.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                Text("No hay tareas aún. ¡Agrega una!", color = Color.Gray)
            }
        } else {
            // LISTA DINÁMICA DE TAREAS
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Ocupa el espacio disponible pero deja lugar al BottomBar
                    .padding(horizontal = 16.dp),
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
                            statusColor = if(task.priority == "Alta") Color.Red else Color(0xFF4CAF50),
                            timeInfo = if(task.duration.isNotEmpty()) "${task.duration} min" else null,
                            hasActions = true
                        )
                        Divider(color = Color(0xFFE0E0E0), modifier = Modifier.padding(vertical = 8.dp))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // FAB
        Box(modifier = Modifier.fillMaxWidth().padding(end = 16.dp, bottom = 20.dp), contentAlignment = Alignment.BottomEnd) {
            FloatingActionButton(
                onClick = { navController.navigate("add_task") },
                containerColor = Color(0xFF00C853)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
            }
        }

        BottomNavigationBar(navController)
    }
}