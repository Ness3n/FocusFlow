package com.example.focusfflow.ui.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// Asegúrate de tener esta dependencia o usa viewModel() si tienes la librería ktx
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.focusfflow.ui.screens.components.BottomNavigationBar

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(), // Inyectamos el ViewModel
    onLoginSuccess: () -> Unit = {},         // Acción al loguearse con éxito
    onRegisterClick: () -> Unit = {}         // Acción para ir a registro
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // --- HEADER (Sin cambios) ---
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
                Text("FF", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text("FocusFlow", fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text("Concentración y hábitos", fontSize = 12.sp, color = Color.Gray)
            }
        }

        Spacer(modifier = Modifier.height(60.dp))

        // --- CARD DE LOGIN ---
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Login", fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Bienvenido", fontSize = 14.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Recuerda tus tareas para poder organizar tus recordatorios.",
                    fontSize = 13.sp, color = Color.Gray, textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // CAMPO EMAIL CONECTADO
                OutlinedTextField(
                    value = viewModel.email, // Leemos del ViewModel
                    onValueChange = { viewModel.onEmailChange(it) }, // Escribimos en el ViewModel
                    label = { Text("Correo electrónico") },
                    placeholder = { Text("user@ejemplo.com", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = viewModel.errorMessage != null, // Mostramos rojo si hay error
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF2196F3)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CAMPO PASSWORD CONECTADO
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text("Contraseña") },
                    placeholder = { Text("-----", color = Color.LightGray) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(), // Ocultar caracteres
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = viewModel.errorMessage != null,
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color(0xFFE0E0E0),
                        focusedBorderColor = Color(0xFF2196F3)
                    ),
                    shape = RoundedCornerShape(8.dp)
                )

                // MENSAJE DE ERROR (Si existe)
                if (viewModel.errorMessage != null) {
                    Text(
                        text = viewModel.errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // BOTÓN DE LOGIN
                Button(
                    onClick = { viewModel.onLoginClick(onLoginSuccess) },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    enabled = !viewModel.isLoading, // Deshabilitar si está cargando
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text("Iniciar sesión", fontSize = 15.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = onRegisterClick) {
                    Text("Crear cuenta", color = Color(0xFF2196F3), fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // ... (Resto del código del FAB y BottomBar igual) ...
        FloatingActionButton(
            onClick = {},
            containerColor = Color(0xFF00C853),
            modifier = Modifier.align(Alignment.End).padding(bottom = 80.dp, end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        BottomNavigationBar()
    }
}