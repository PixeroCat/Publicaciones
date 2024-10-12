package com.example.publicaciones.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserPublications(
    @Embedded val user: Usuarios,
    @Relation(
        parentColumn = "id",
        entityColumn = "usuarios_id"
    )
    val publications: List<Publicaciones>
)
