package com.portafoliowebmariano.notasapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class NoteView(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title : String,
    var note : String,
    var checked: Boolean
)
