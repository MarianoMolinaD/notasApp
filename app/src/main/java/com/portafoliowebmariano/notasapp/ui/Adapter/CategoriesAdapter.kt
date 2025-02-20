package com.portafoliowebmariano.notasapp.ui.Adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.model.Categoria
import com.portafoliowebmariano.notasapp.ui.ViewHolder.CategoriesViewHolder


class CategoriesAdapter (private val listCategorias : MutableList<Categoria>,private val getColor:(Int) -> Unit):RecyclerView.Adapter<CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CategoriesViewHolder(layoutInflater.inflate(R.layout.items_categorias,parent,false))
    }

    override fun getItemCount(): Int = listCategorias.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = listCategorias[position]
        holder.bin(item, getColor)
    }
}