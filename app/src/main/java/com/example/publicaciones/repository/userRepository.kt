package com.example.publicaciones.repository

import com.example.publicaciones.dao.UserDao
import com.example.publicaciones.model.UserPublications
import com.example.publicaciones.model.Usuarios

class UserRepository(private val userDao: UserDao) {
    suspend fun insertar(user: Usuarios) {
        userDao.insert(user)
    }

    suspend fun getAllUsers(): List<Usuarios> {
        return userDao.getAllUsers()
    }

    suspend fun deleteById(userId: Int): Int {
        return userDao.deleteById(userId) // Llama al método deleteById del DAO
    }

    suspend fun delete(user: Usuarios) {
        userDao.delete(user) // Llamada al método delete del DAO
    }

    suspend fun getUserPublications(userId: Int): List<UserPublications> {
        return userDao.getUserPublications(userId) // Llamada al DAO
    }
}