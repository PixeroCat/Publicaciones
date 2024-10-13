package com.example.publicaciones.screen

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.publicaciones.model.Usuarios
import com.example.publicaciones.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavHostController, userRepository: UserRepository) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Registro") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de Usuario") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    val newUser = Usuarios(
                        username = username,
                        password = password
                    )

                    scope.launch {
                        // Insertar el nuevo usuario y obtener el ID generado
                        val insertedUserId = withContext(Dispatchers.IO) {
                            userRepository.insertar(newUser)  // Devuelve el ID del usuario insertado
                        }

                        // Comprobamos si el ID del usuario es válido
                        if (insertedUserId > 0) {
                            Toast.makeText(context, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show()

                            // Navegamos a Home pasando el ID del nuevo usuario
                            navController.navigate("home/$insertedUserId")
                        } else {
                            Toast.makeText(context, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ) {
                Text("Registrar")
            }
        }
    }
}
