package com.portafoliowebmariano.notasapp.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.portafoliowebmariano.notasapp.databinding.DialogAddNotesView2Binding
import com.portafoliowebmariano.notasapp.model.NoteView

object DialogAddNoteView {

    fun showDialogAddNotesView(context : Context, addNote:(noteView: NoteView) -> Unit,
                               getNotes:() -> Unit){
        val inflater = LayoutInflater.from(context)
        val binding = DialogAddNotesView2Binding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()

        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        binding.btnAddNote.setOnClickListener {
            val noteTitle = binding.etTitle.text.toString().trim()
            val note = binding.etNote.text.toString().trim()

            val noteView = NoteView(title = noteTitle, note = note, checked = false)
            addNote(noteView)
            alertDialog.dismiss()
            getNotes()
        }
        alertDialog.show()
    }
}