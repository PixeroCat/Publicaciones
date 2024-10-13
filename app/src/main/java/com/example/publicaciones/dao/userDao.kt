package com.example.publicaciones.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.publicaciones.model.UserPublications
import com.example.publicaciones.model.Usuarios
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: Usuarios): Long

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<Usuarios>>

    @Update
    suspend fun update(user: Usuarios)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Int): Int

    @Delete
    suspend fun delete(user: Usuarios)

    @Query("SELECT * FROM users WHERE id= :userId")
    fun getUserPublications(userId: Int): Flow<List<UserPublications>>  // Observar publicaciones de usuario en tiempo real

}