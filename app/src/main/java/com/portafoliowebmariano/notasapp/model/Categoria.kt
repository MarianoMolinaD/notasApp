package com.portafoliowebmariano.notasapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categoria(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    var nombreCategoria : String,
    var color : Int
)