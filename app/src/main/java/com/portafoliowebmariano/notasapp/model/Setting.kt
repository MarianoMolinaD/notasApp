package com.portafoliowebmariano.notasapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Setting (
    @PrimaryKey() val id : Int = 0,
    var confirDelete: Boolean
)