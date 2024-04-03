package com.portafoliowebmariano.notasapp.repository

import android.content.Context
import com.portafoliowebmariano.notasapp.data.DBNote
import com.portafoliowebmariano.notasapp.data.DaoNotes
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.model.Setting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotesRepository (context: Context){
private var daoNotes : DaoNotes = DBNote.getDataBase(context).daoNotes()

    suspend fun addNote(note: Note){
        withContext(Dispatchers.IO){
            daoNotes.addNotes(note)
        }
    }

    suspend fun addSetting(setting: Setting){
        withContext(Dispatchers.IO){
            daoNotes.addSetting(setting)
        }
    }

    suspend fun getListNotes(): MutableList<Note>{
        return withContext(Dispatchers.IO){
            daoNotes.getNotes()
        }
    }

    suspend fun getSetting(): Setting{
        return withContext(Dispatchers.IO){
            daoNotes.getSetting()
        }
    }

    suspend fun deleteNote(note: Note){
        withContext(Dispatchers.IO){
            daoNotes.deleteNote(note)
        }
    }

    suspend fun updateNote(note: Note){
        withContext(Dispatchers.IO){
            daoNotes.updateNote(note)
        }
    }

    suspend fun updateSetting(setting: Setting){
        withContext(Dispatchers.IO){
            daoNotes.updateSetting(setting)
        }
    }

}