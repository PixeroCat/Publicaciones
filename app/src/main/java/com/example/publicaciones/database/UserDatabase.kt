package com.example.publicaciones.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.publicaciones.dao.PublicationDao
import com.example.publicaciones.dao.UserDao
import com.example.publicaciones.model.Publicaciones
import com.example.publicaciones.model.Usuarios

@Database(entities = [Usuarios::class, Publicaciones::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun publicationDao(): PublicationDao

    // Compain Object Se usa para definir miembros estaticos en kotlin
    // Volatile permite que cualquier hilo que acceda a la variable tenga la version mas actualizada

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        // Permite crear la instancia de la base de datos

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "publications_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}