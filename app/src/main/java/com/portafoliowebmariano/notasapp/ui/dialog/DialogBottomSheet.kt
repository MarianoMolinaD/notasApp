package com.portafoliowebmariano.notasapp.ui.dialog

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.BottomShetDialogBinding
import com.portafoliowebmariano.notasapp.model.Categoria
import com.portafoliowebmariano.notasapp.ui.Adapter.CategoriesAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager

object DialogBottomSheet {

    fun showDialogBottomSheet(
        context: Context,
        listCategorias: MutableList<Categoria>,
        addCategory: (categoria: Categoria) -> Unit
    ) {
        val inflater = LayoutInflater.from(context)
        val binding = BottomShetDialogBinding.inflate(inflater)
        val bottomSheetDialog = BottomSheetDialog(context)
        var selectedColor: Int? = null
        var selectedButton: Button? = null // Variable para guardar el botón seleccionado
        val originalButtonColors = mutableMapOf<Button, Int>() // Guarda los colores originales de los botones

        bottomSheetDialog.setContentView(binding.root)

        // Lista de colores disponibles
        val colors = listOf(
            ContextCompat.getColor(context, R.color.color1),
            ContextCompat.getColor(context, R.color.color2),
            ContextCompat.getColor(context, R.color.color3),
            ContextCompat.getColor(context, R.color.color4),
            ContextCompat.getColor(context, R.color.color5),
            ContextCompat.getColor(context, R.color.color6),
            ContextCompat.getColor(context, R.color.color7),
            ContextCompat.getColor(context, R.color.color8)
        )

        // Lista de botones de colores
        val colorButtons: List<Button> = listOf(
            binding.color1, binding.color2, binding.color3, binding.color4,
            binding.color5, binding.color6, binding.color7, binding.color8
        )

        // Asignar colores a los botones y manejar la selección
        if (colorButtons.isNotEmpty() && colors.isNotEmpty()) {
            val size = minOf(colorButtons.size, colors.size) // Asegurarnos de que no haya desbordamiento de índices

            colorButtons.take(size).forEachIndexed { index, button ->
                val color = colors[index] // Obtener el color correspondiente
                button.backgroundTintList = ColorStateList.valueOf(color)

                button.setOnClickListener {
                    // Restaurar el color del botón previamente seleccionado
                    selectedButton?.backgroundTintList = ColorStateList.valueOf(originalButtonColors[selectedButton] ?: color)
                    // Guardar el botón y color seleccionado
                    selectedButton = button
                    originalButtonColors[button] = color // Guardar el color original del botón
                    button.backgroundTintList = ColorStateList.valueOf(Color.GRAY) // Cambiar el color del botón seleccionado
                    selectedColor = color // Guardar el color seleccionado en la variable
                }
            }
        } else {
            // Manejar el caso si las listas están vacías o no coinciden en tamaño
            Toast.makeText(context, "Faltan colores o botones", Toast.LENGTH_SHORT).show()
        }

        // Guardar la categoría
        binding.ivSaveCategory.setOnClickListener {
            val color: Int? = selectedColor
            val nombre = binding.etCategory.text.toString().trim()

            // Validar que el nombre no esté vacío
            if (nombre.isEmpty()) {
                Toast.makeText(context, "Por favor, ingresa un nombre para la categoría", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validar que se haya seleccionado un color
            if (color == null) {
                Toast.makeText(context, "Por favor, selecciona un color", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Crear la nueva categoría y agregarla
            val categoria = Categoria(nombreCategoria = nombre, color = color)
            addCategory(categoria)
            bottomSheetDialog.dismiss() // Cerrar el diálogo después de agregar la categoría
        }

        // Inicializar el adaptador para mostrar las categorías existentes
        @SuppressLint("NotifyDataSetChanged")
        fun initAdapter(list: MutableList<Categoria>) {
            val recycler = binding.svCategorias

            val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            recycler.layoutManager = layoutManager
            recycler.itemAnimator = null

            val adapter = CategoriesAdapter(list)
            recycler.adapter = adapter
        }

        // Mostrar las categorías existentes
        initAdapter(listCategorias)
        bottomSheetDialog.show()
    }
}