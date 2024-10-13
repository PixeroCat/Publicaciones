package com.example.publicaciones.repository

import com.example.publicaciones.dao.UserDao
import com.example.publicaciones.model.UserPublications
import com.example.publicaciones.model.Usuarios
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    suspend fun insertar(user: Usuarios): Long {
        return userDao.insert(user)
    }

    suspend fun update(user: Usuarios) {
        userDao.update(user)
    }

    fun getAllUsers(): Flow<List<Usuarios>> {
        return userDao.getAllUsers()
    }

    suspend fun deleteById(userId: Int): Int {
        return userDao.deleteById(userId) // Llama al método deleteById del DAO
    }

    suspend fun delete(user: Usuarios) {
        userDao.delete(user) // Llamada al método delete del DAO
    }

    fun getUserPublicationsFlow(userId: Int): Flow<List<UserPublications>> {
        return userDao.getUserPublications(userId)  // Observar publicaciones del usuario en tiempo real
    }
}