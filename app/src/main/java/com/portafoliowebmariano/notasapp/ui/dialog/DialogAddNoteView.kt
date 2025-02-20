package com.portafoliowebmariano.notasapp.ui.dialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.portafoliowebmariano.notasapp.databinding.DialogAddNotesView2Binding
import com.portafoliowebmariano.notasapp.model.Categoria
import com.portafoliowebmariano.notasapp.model.NoteView
import com.portafoliowebmariano.notasapp.ui.Adapter.CategoriesAdapter

object DialogAddNoteView {
    fun showDialogAddNotesView(context : Context,listCategorias: MutableList<Categoria> ,addNote:(noteView: NoteView) -> Unit,
                               getNotes:() -> Unit){
        val inflater = LayoutInflater.from(context)
        val binding = DialogAddNotesView2Binding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(context).create()
        var color : Int = 0

        alertDialog.setCancelable(false)
        alertDialog.setView(binding.root)

        binding.btnCancel.setOnClickListener {
            alertDialog.dismiss()
        }

        binding.btnAddNote.setOnClickListener {

            val noteTitle = binding.etTitle.text.toString().trim()
            val note = binding.etNote.text.toString().trim()
            val color = color

            if (color == 0 || noteTitle.isEmpty() || note.isEmpty()){
                Toast.makeText(context, "Debe llenar todos los datos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val noteView = NoteView(title = noteTitle, note = note, checked = false, color = color)
            addNote(noteView)
            alertDialog.dismiss()
            getNotes()
        }

        fun selectColor(colorE : Int){
            color = colorE

        }
        @SuppressLint("NotifyDataSetChanged")
        fun initAdapter(list: MutableList<Categoria>) {

            val recycler = binding.categoriesAddnote

            val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            recycler.layoutManager = layoutManager
            recycler.itemAnimator = null

            val adapter = CategoriesAdapter(list,{selectColor(it)})
            recycler.adapter = adapter
        }

        // Mostrar las categorías existentes
        initAdapter(listCategorias)

        alertDialog.show()
    }
}