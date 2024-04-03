package com.portafoliowebmariano.notasapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.model.Setting

@Dao
interface DaoNotes {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNotes(note:Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSetting(setting: Setting)

    @Query("SELECT * FROM Note")
    suspend fun getNotes() : MutableList<Note>

    @Query("SELECT * FROM Setting WHERE id = 1")
    suspend fun getSetting() : Setting

    @Update
    suspend fun updateNote(note:Note)

    @Update
    suspend fun updateSetting(setting: Setting)

    @Delete
    suspend fun deleteNote(note: Note)
}