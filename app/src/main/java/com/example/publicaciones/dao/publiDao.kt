package com.example.publicaciones.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.publicaciones.model.Publicaciones
import com.example.publicaciones.model.PublicationWithUser
import kotlinx.coroutines.flow.Flow

@Dao
interface PublicationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(publication: Publicaciones)

    // Cambiado "publication" por "publicaciones"
    @Query("SELECT * FROM publicaciones")
    fun getAllPublications(): Flow<List<Publicaciones>>

    @Update
    suspend fun update(publication: Publicaciones)

    // Cambiado "publication" por "publicaciones"
    @Query("DELETE FROM publicaciones WHERE id = :publicationId")
    suspend fun deleteById(publicationId: Int): Int

    @Delete
    suspend fun delete(publication: Publicaciones)

    @Query("SELECT * FROM publicaciones WHERE usuario_id = :userId")
    fun getPublicationsByUserId(userId: Int): Flow<List<Publicaciones>>  // Observar publicaciones por usuario en tiempo real

    @Query("SELECT * FROM publicaciones")
    fun getPublicationsWithUsers(): Flow<List<PublicationWithUser>>
}
