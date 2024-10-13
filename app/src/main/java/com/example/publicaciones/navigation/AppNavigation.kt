package com.example.publicaciones.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.publicaciones.repository.PublicationRepository
import com.example.publicaciones.repository.UserRepository
import com.example.publicaciones.screen.HomeScreen
import com.example.publicaciones.screen.LoginScreen
import com.example.publicaciones.screen.SignUpScreen

@Composable
fun AppNavigation(userRepository: UserRepository, publicationRepository: PublicationRepository, currentUserId: Int) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            // Pasamos el UserRepository directamente a la pantalla de Login
            LoginScreen(navController = navController, userRepository = userRepository)
        }
        composable("signup") {
            // Pasamos el UserRepository directamente a la pantalla de Sign Up
            SignUpScreen(navController = navController, userRepository = userRepository)
        }
        composable("home/{currentUserId}") { backStackEntry ->
            // Obtenemos el currentUserId de los argumentos de la navegación
            val currentUserId = backStackEntry.arguments?.getString("currentUserId")?.toInt() ?: 0
            // Pasamos el currentUserId dinámico a HomeScreen
            HomeScreen(
                navController = navController,
                userRepository = userRepository,
                publicationRepository = publicationRepository,
                currentUserId = currentUserId
            )
        }
    }
}