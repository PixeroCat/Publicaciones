package com.example.publicaciones.model

import androidx.room.Embedded
import androidx.room.Relation

data class PublicationWithUser(
    @Embedded val publication: Publicaciones,
    @Relation(
        parentColumn = "usuario_id",
        entityColumn = "id"
    )
    val user: Usuarios
)