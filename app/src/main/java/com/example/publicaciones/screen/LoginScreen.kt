package com.example.publicaciones.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import android.widget.Toast
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavHostController
import com.example.publicaciones.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController, userRepository: UserRepository) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Login") }
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
                    scope.launch {
                        val user = withContext(Dispatchers.IO) {
                            val usersList = userRepository.getAllUsers().first()
                            usersList.find { it.username == username && it.password == password }
                        }

                        if (user != null) {
                            Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()

                            // Navegar a Home y pasar el id del usuario autenticado
                            navController.navigate("home/${user.id}")
                        } else {
                            Toast.makeText(context, "Login fallido", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            ) {
                Text("Iniciar Sesión")
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Texto clickeable para ir a la pantalla de registro
            TextButton(onClick = {navController.navigate("signup")}) {
                Text("¿No tienes cuenta? Crea una aquí.")
            }
        }
    }
}


