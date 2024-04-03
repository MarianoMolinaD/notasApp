package com.portafoliowebmariano.notasapp.ui.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.ui.ViewHolder.NotesViewHolder

class NotesAdapter (private val listNotes :MutableList<Note>, private val onclickItemDelete: (Note) -> Unit,private val onclickItemUpdate: (Note) -> Unit): RecyclerView.Adapter<NotesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(layoutInflater.inflate(R.layout.item_notes,parent,false))
    }

    override fun getItemCount(): Int = listNotes.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val item = listNotes[position]
        holder.bin(item, onclickItemDelete,onclickItemUpdate)
    }
}