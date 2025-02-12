package com.portafoliowebmariano.notasapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.model.NoteView
import com.portafoliowebmariano.notasapp.ui.ViewHolder.NotesViewViewHolder

class NotesViewAdapter(private val listNotesView: MutableList<NoteView>,private val onclickDelete:(NoteView) -> Unit): RecyclerView.Adapter<NotesViewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotesViewViewHolder(layoutInflater.inflate(R.layout.item_notes_view,parent,false))
    }

    override fun getItemCount(): Int = listNotesView.size

    override fun onBindViewHolder(holder: NotesViewViewHolder, position: Int) {
        val item = listNotesView[position]
        holder.bin(item,onclickDelete)
    }
}