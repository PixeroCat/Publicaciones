package com.example.publicaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.publicaciones.dao.PublicationDao
import com.example.publicaciones.dao.UserDao
import com.example.publicaciones.database.UserDatabase
import com.example.publicaciones.navigation.AppNavigation
import com.example.publicaciones.repository.PublicationRepository
import com.example.publicaciones.repository.UserRepository
import com.example.publicaciones.ui.theme.PublicacionesTheme

class MainActivity : ComponentActivity() {

    private lateinit var userDao: UserDao
    private lateinit var userRepository: UserRepository
    private lateinit var publicationDao: PublicationDao
    private lateinit var publicationRepository: PublicationRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = UserDatabase.getDatabase(applicationContext)
        userDao = db.userDao()
        publicationDao = db.publicationDao()

        userRepository = UserRepository(userDao)
        publicationRepository = PublicationRepository(publicationDao)

        val currentUserId = 1

        enableEdgeToEdge()
        setContent {
            PublicacionesTheme {
                AppNavigation(userRepository = userRepository,
                    publicationRepository = publicationRepository,
                    currentUserId = currentUserId)
            }
        }
    }
}
