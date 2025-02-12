package com.portafoliowebmariano.notasapp.ui.ViewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.notasapp.databinding.ItemNotesViewBinding
import com.portafoliowebmariano.notasapp.model.NoteView

class NotesViewViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val binding = ItemNotesViewBinding.bind(view)

   fun bin(lista : NoteView,onclickDele:(NoteView) -> Unit){
       binding.tvTitleNote.text = lista.title
       binding.tvBodyNote.text = lista.note

       binding.btnDeleteNote.setOnClickListener {
         onclickDele(lista)
       }
   }
}