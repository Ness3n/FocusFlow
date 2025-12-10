package com.example.focusfflow.ui.screens.tasks.add

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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.focusfflow.ui.screens.components.AppHeader
import com.example.focusfflow.ui.screens.components.BottomNavigationBar
import androidx.compose.ui.platform.LocalContext

@Composable
fun AddTasksScreen(
    navController: NavController,
    viewModel: AddTasksViewModel = viewModel(
        factory = androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner.current?.let {
            androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(
                LocalContext.current.applicationContext as android.app.Application
            )
        } ?: throw IllegalStateException("No ViewModelStoreOwner available")
    )
) {
    // Definimos colores predeterminados para los Inputs que se adapten al tema
    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
        cursorColor = MaterialTheme.colorScheme.primary,
        focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
        unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AppHeader()

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Add tasks",
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
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Agregar ítem", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(modifier = Modifier.height(12.dp))

                // Mostrar error si existe
                if (viewModel.errorMessage != null) {
                    Text(
                        text = viewModel.errorMessage!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // TÍTULO
                OutlinedTextField(
                    value = viewModel.title,
                    onValueChange = { viewModel.onTitleChange(it) },
                    placeholder = { Text("Titular de la actividad") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = textFieldColors,
                    shape = RoundedCornerShape(8.dp),
                    enabled = !viewModel.isLoading
                )

                Spacer(modifier = Modifier.height(12.dp))

                // DESCRIPCIÓN
                OutlinedTextField(
                    value = viewModel.description,
                    onValueChange = { viewModel.onDescriptionChange(it) },
                    placeholder = { Text("Descripción (opcional)") },
                    modifier = Modifier.fillMaxWidth().height(100.dp),
                    colors = textFieldColors,
                    shape = RoundedCornerShape(8.dp),
                    enabled = !viewModel.isLoading
                )

                Spacer(modifier = Modifier.height(16.dp))

                // LABELS
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Prioridad", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                    Text("Duración (min)", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurface)
                }
                Spacer(modifier = Modifier.height(8.dp))

                // INPUTS
                Row(modifier = Modifier.fillMaxWidth()) {
                    // Prioridad
                    OutlinedTextField(
                        value = viewModel.priority,
                        onValueChange = { viewModel.onPriorityChange(it) },
                        placeholder = { Text("Alta/Media") },
                        modifier = Modifier.weight(1f),
                        colors = textFieldColors,
                        shape = RoundedCornerShape(8.dp),
                        enabled = !viewModel.isLoading
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    // Duración
                    OutlinedTextField(
                        value = viewModel.duration,
                        onValueChange = { viewModel.onDurationChange(it) },
                        placeholder = { Text("Min") },
                        modifier = Modifier.width(90.dp),
                        colors = textFieldColors,
                        shape = RoundedCornerShape(8.dp),
                        enabled = !viewModel.isLoading
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        viewModel.saveTask { navController.popBackStack() }
                    },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !viewModel.isLoading
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text("Guardar datos", fontSize = 14.sp, color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        BottomNavigationBar(navController)
    }
}