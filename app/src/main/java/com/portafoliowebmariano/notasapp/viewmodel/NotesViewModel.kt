package com.portafoliowebmariano.notasapp.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.portafoliowebmariano.notasapp.model.Categoria
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.model.NoteView
import com.portafoliowebmariano.notasapp.model.Setting
import com.portafoliowebmariano.notasapp.repository.NotesRepository
import com.portafoliowebmariano.notasapp.ui.dialog.DialogAddNote.showDialogAddNotes
import com.portafoliowebmariano.notasapp.ui.dialog.DialogAddNoteView.showDialogAddNotesView
import com.portafoliowebmariano.notasapp.ui.dialog.DialogBottomSheet
import com.portafoliowebmariano.notasapp.ui.dialog.DialogDeleteNote
import com.portafoliowebmariano.notasapp.ui.dialog.DialogDeleteNote.showDialogDeleteNotes
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val context = getApplication<Application>()
    private val notesRepository = NotesRepository(context)

    val listNotes = MutableLiveData<MutableList<Note>>()
    val setting = MutableLiveData<Setting>()
    val listNotesView = MutableLiveData<MutableList<NoteView>>()

    fun showDialogAddNote(context: Context) {
        showDialogAddNotes(context,{addNote(it)},{getListNotes()})
    }

    fun showDialogAddNoteView(context: Context){
        showDialogAddNotesView(context, { addNoteView(it)},{getListNotesView()})
    }

    fun showDialogDeleteNote(context: Context, note: Note,deleteNote:()-> Unit){
        showDialogDeleteNotes(context,note){deleteNote()}
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                notesRepository.addNote(note)
            } catch (e: Exception) {

            }
        }
    }

    fun addNoteView(noteView: NoteView){
        viewModelScope.launch {
            try {
                notesRepository.addNoteView(noteView)
            }catch (e: Exception){

            }
        }
    }

    fun addCategoria(categoria: Categoria){
        viewModelScope.launch {
            try {
                notesRepository.addCategory(categoria)
            }catch (e: Exception){

            }
        }
    }

    fun showDialogBottomSheet(context: Context){
        DialogBottomSheet.showDialogBottomSheet(context){addCategoria(it)}
    }

    fun getListNotes() {
        viewModelScope.launch {
            try {
                listNotes.postValue(notesRepository.getListNotes())

            } catch (e: Exception) {

            }
        }
    }
    fun getListNotesView(){
        viewModelScope.launch {
            try {
                listNotesView.postValue(notesRepository.getListNotesView())
            }catch (e:Exception){

            }
        }
    }



    fun deleteNote(note: Note) {
        viewModelScope.launch {
            try {
                notesRepository.deleteNote(note)
                listNotes.postValue(notesRepository.getListNotes())
            } catch (e: Exception) {

            }
        }
    }

    fun deleteNoteView(Note:NoteView){
        viewModelScope.launch {
            try {
                notesRepository.deleteNoteView(Note)
                listNotesView.postValue(notesRepository.getListNotesView())
            }catch (e: Exception){

            }
        }
    }

    fun updateNote(note: Note){
        viewModelScope.launch{
            try {
                notesRepository.updateNote(note)
                listNotes.postValue(notesRepository.getListNotes())
            }catch (e: Exception){

            }
        }
    }

    fun addSetting(setting: Setting){
        viewModelScope.launch {
            try {
                notesRepository.addSetting(setting)
            }catch (e: Exception){

            }
        }
    }
    fun updateSetting(setting: Setting){
        viewModelScope.launch {
            try {
                notesRepository.updateSetting(setting)
            }catch (e: Exception){

            }
        }
    }

    fun getSetting() {
        viewModelScope.launch {
            try {
                setting.postValue(notesRepository.getSetting())
            }catch (e:Exception){
                Toast.makeText(context,"NO hay datos",Toast.LENGTH_SHORT).show()

            }
        }
    }
}