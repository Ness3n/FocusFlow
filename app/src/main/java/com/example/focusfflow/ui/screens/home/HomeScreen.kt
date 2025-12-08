package com.example.focusfflow.ui.screens.home


import androidx.compose.foundation.background
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
import com.example.focusfflow.ui.screens.components.MenuCard
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
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
                    .background(Color(0xFF2196F3), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "FF",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "FocusFlow",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
                Text(
                    text = "Concentración y hábitos",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Main menu",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Hola, usuario",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjetas del menú
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    title = "Mis Tareas",
                    subtitle = "Explora y gestiona",
                    modifier = Modifier.weight(1f)
                )
                MenuCard(
                    title = "Modo Bloqueo",
                    subtitle = "Concentra tú atención",
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuCard(
                    title = "Recordatorios",
                    subtitle = "Recuerda tu misión",
                    modifier = Modifier.weight(1f)
                )
                MenuCard(
                    title = "Configuración",
                    subtitle = "Modifica tu perfil",
                    modifier = Modifier.weight(1f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Actividad reciente",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Hacer estudia...",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Prioridad: Media",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
                TextButton(onClick = {}) {
                    Text(
                        text = "Ver Tareas",
                        color = Color(0xFF2196F3),
                        fontSize = 12.sp
                    )
                }
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