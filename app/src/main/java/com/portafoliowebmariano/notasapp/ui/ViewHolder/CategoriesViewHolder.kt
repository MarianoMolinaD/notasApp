package com.portafoliowebmariano.notasapp.ui.ViewHolder

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.notasapp.databinding.ItemsCategoriasBinding
import com.portafoliowebmariano.notasapp.model.Categoria
import com.portafoliowebmariano.notasapp.ui.Adapter.CategoriesAdapter

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemsCategoriasBinding.bind(view)

    fun bin(item: Categoria) {
        binding.etCategoria.text = item.nombreCategoria
        binding.cvContainer.setCardBackgroundColor(item.color)

        binding.cvContainer.setOnClickListener {
            Log.e("Categorias",item.id.toString())
        }
    }
}