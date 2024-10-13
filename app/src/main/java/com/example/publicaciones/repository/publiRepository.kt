package com.example.publicaciones.repository

import com.example.publicaciones.dao.PublicationDao
import com.example.publicaciones.dao.UserDao
import com.example.publicaciones.model.Publicaciones
import com.example.publicaciones.model.PublicationWithUser
import com.example.publicaciones.model.Usuarios
import kotlinx.coroutines.flow.Flow

class PublicationRepository(private val publicationDao: PublicationDao) {
    suspend fun insertar(publication: Publicaciones) {
        publicationDao.insert(publication)
    }

    suspend fun update(publication: Publicaciones){
        publicationDao.update(publication)
    }

    fun getPublicationsByUser(userId: Int): Flow<List<Publicaciones>> {
        return publicationDao.getPublicationsByUserId(userId)
    }

    fun getAllPublications(): Flow<List<Publicaciones>> {
        return publicationDao.getAllPublications()
    }

    suspend fun deleteById(publicationId: Int): Int {
        return publicationDao.deleteById(publicationId) // Llama al método deleteById del DAO
    }

    suspend fun delete(publication: Publicaciones) {
        publicationDao.delete(publication) // Llamada al método delete del DAO
    }

    fun getPublicationsWithUsers(): Flow<List<PublicationWithUser>> {
        return publicationDao.getPublicationsWithUsers()
    }
}