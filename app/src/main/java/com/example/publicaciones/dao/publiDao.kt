package com.example.publicaciones.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.publicaciones.model.Publicaciones

@Dao
interface PublicationDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(publication: Publicaciones)

    @Query("SELECT * FROM publication")
    suspend fun getAllPublications(): List<Publicaciones>

    @Update
    suspend fun update(publication: Publicaciones)

    @Query("DELETE FROM publication WHERE id = :publicationId")
    suspend fun deleteById(publicationId: Int): Int

    @Delete
    suspend fun delete(publication: Publicaciones)

}
