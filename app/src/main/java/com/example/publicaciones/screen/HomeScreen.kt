package com.example.publicaciones.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import com.example.publicaciones.model.Publicaciones
import com.example.publicaciones.repository.PublicationRepository
import com.example.publicaciones.repository.UserRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    userRepository: UserRepository,
    publicationRepository: PublicationRepository,
    currentUserId: Int
) {
    var newPostText by remember { mutableStateOf("") }
    var newPostTitle by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val publicationsWithUsersFlow = publicationRepository.getPublicationsWithUsers()
    val publicationsWithUsers by publicationsWithUsersFlow.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") },
                actions = {
                    // Botón de cerrar sesión
                    Button(onClick = {
                        // Aquí podrías limpiar la sesión o redirigir al login
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    }) {
                        Text("Cerrar Sesión")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = newPostTitle,
                onValueChange = { newPostTitle = it },
                label = { Text("Título de la publicación") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = newPostText,
                onValueChange = { newPostText = it },
                label = { Text("Escribe una nueva publicación") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    if (newPostText.isNotEmpty()) {
                        val newPublication = Publicaciones(
                            titulo = newPostTitle,
                            cuerpo = newPostText,
                            usuario_id = currentUserId
                        )

                        scope.launch {
                            publicationRepository.insertar(newPublication)
                            newPostTitle = ""  // Limpiar el campo de titulo
                            newPostText = ""  // Limpiar el campo de texto
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Publicar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                items(publicationsWithUsers) { publicationWithUser ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = "Título: ${publicationWithUser.publication.titulo}") // Muestra el título
                        Text(text = "Por: ${publicationWithUser.user.username}")  // Muestra el nombre del usuario
                        Text(text = publicationWithUser.publication.cuerpo)  // Muestra el contenido de la publicación
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}
