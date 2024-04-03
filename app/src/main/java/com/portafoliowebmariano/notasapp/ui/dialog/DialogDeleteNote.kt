package com.portafoliowebmariano.notasapp.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.portafoliowebmariano.notasapp.databinding.DialogAddNoteBinding
import com.portafoliowebmariano.notasapp.databinding.DialogDeleteNoteBinding
import com.portafoliowebmariano.notasapp.model.Note

object DialogDeleteNote {

    fun showDialogDeleteNotes(context: Context,note: Note, deletNote:(note:Note) -> Unit){
        val inflater = LayoutInflater.from(context)
        val binding = DialogDeleteNoteBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.tvNote.text = note.note
        binding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }
        binding.btnDelete.setOnClickListener {
            deletNote(note)
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}