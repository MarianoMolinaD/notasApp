package com.portafoliowebmariano.notasapp.ui.ViewHolder
import android.graphics.Paint
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.notasapp.databinding.ItemNotesBinding
import com.portafoliowebmariano.notasapp.model.Note

class NotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemNotesBinding.bind(view)

    fun bin(item: Note, onclickItemDelete: (Note) -> Unit, onclickItemUpdate: (Note) -> Unit){

        binding.tvNote.text = item.note
        if (item.checked) {
            binding.cbNote.isChecked = true
            binding.tvNote.paintFlags = binding.tvNote.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.tvNote.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            binding.cbNote.isChecked = false
        }
        binding.cbNote.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                binding.tvNote.paintFlags = binding.tvNote.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                onclickItemUpdate(Note(id = item.id, note = item.note, true))
            } else {
                binding.tvNote.paintFlags =
                    binding.tvNote.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
                onclickItemUpdate(Note(id = item.id, note = item.note, false))
            }
        }
        binding.btnDelete.setOnClickListener {
            onclickItemDelete(item)
        }
        binding.btnUpdate.setOnClickListener {
            modifierItem()
            binding.etUpdate.setText(item.note)
        }
        binding.btnCLose.setOnClickListener {
            binding.tvNote.isVisible = true
            binding.btnDelete.isVisible = true
            binding.btnUpdate.isVisible = true
            binding.etUpdate.isVisible = false
            binding.btnCheck.isVisible = false
            binding.btnCLose.isVisible = false
        }
        binding.btnCheck.setOnClickListener {
            val note = binding.etUpdate.text.toString().trim()
            onclickItemUpdate(Note(id = item.id, note = note, checked = item.checked))
        }
    }

    private fun modifierItem() {
        binding.tvNote.isVisible = false
        binding.btnDelete.isVisible = false
        binding.btnUpdate.isVisible = false
        binding.etUpdate.isVisible = true
        binding.etUpdate.setSelection(binding.etUpdate.text.length)
        binding.btnCheck.isVisible = true
        binding.btnCLose.isVisible = true
    }
}