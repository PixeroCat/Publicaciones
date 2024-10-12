package com.example.publicaciones.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "publication",
    foreignKeys = [
        ForeignKey(
            entity = Usuarios::class,
            parentColumns = ["id"],
            childColumns = ["usuario_id"],
            onDelete = ForeignKey.CASCADE // Elimina publicaciones del usuario si es eliminado
        )
    ])
data class Publicaciones(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val cuerpo: String,
    val usuario_id: Int // Clave foranea
)