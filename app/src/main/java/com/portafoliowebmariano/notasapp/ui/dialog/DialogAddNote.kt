package com.portafoliowebmariano.notasapp.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.portafoliowebmariano.notasapp.databinding.DialogAddNoteBinding
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.viewmodel.NotesViewModel

object DialogAddNote {

    fun showDialogAddNotes(context: Context, addNote:(note: Note)-> Unit, getNotes:()-> Unit){
        val inflater = LayoutInflater.from(context)
        val binding = DialogAddNoteBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        binding.btnAddNote.setOnClickListener {
            val noteDescription = binding.etAddNote.text.toString().trim()
            val note = Note(note = noteDescription)
            addNote(note)
            alertDialog.dismiss()
            getNotes()
        }
        alertDialog.show()
    }
}