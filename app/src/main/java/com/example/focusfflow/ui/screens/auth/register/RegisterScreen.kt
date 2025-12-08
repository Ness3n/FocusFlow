package com.example.focusfflow.ui.screens.auth.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.lifecycle.viewmodel.compose.viewModel // Importante
import com.example.focusfflow.ui.screens.components.BottomNavigationBar

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = viewModel(), // Inyectamos el ViewModel
    onRegisterSuccess: () -> Unit = {},         // Navegación al éxito
    onLoginClick: () -> Unit = {}               // Navegación para volver
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header simple para registro
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Crear Cuenta",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Card de Registro
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
                Text(
                    text = "Regístrate",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                // CAMPO NOMBRE
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO EMAIL
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.onEmailChange(it) },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO PASSWORD
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.onPasswordChange(it) },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    shape = RoundedCornerShape(8.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                // CAMPO CONFIRMAR PASSWORD
                OutlinedTextField(
                    value = viewModel.confirmPassword,
                    onValueChange = { viewModel.onConfirmPasswordChange(it) },
                    label = { Text("Confirmar contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = viewModel.errorMessage != null,
                    shape = RoundedCornerShape(8.dp)
                )

                // MENSAJE DE ERROR
                if (viewModel.errorMessage != null) {
                    Text(
                        text = viewModel.errorMessage ?: "",
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // BOTÓN REGISTRAR
                Button(
                    onClick = { viewModel.onRegisterClick(onRegisterSuccess) },
                    modifier = Modifier.fillMaxWidth().height(48.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = RoundedCornerShape(8.dp),
                    enabled = !viewModel.isLoading
                ) {
                    if (viewModel.isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text("Registrarse", fontSize = 15.sp)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // BOTÓN VOLVER AL LOGIN
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("¿Ya tienes cuenta?", fontSize = 13.sp, color = Color.Gray)
                    TextButton(onClick = onLoginClick) {
                        Text("Inicia sesión", color = Color(0xFF2196F3), fontSize = 13.sp)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomNavigationBar()
    }
}