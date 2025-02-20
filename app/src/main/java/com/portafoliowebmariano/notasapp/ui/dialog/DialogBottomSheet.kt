package com.portafoliowebmariano.notasapp.ui.dialog
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.BottomShetDialogBinding
import com.portafoliowebmariano.notasapp.model.Categoria
import com.portafoliowebmariano.notasapp.ui.Adapter.CategoriesAdapter
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.portafoliowebmariano.notasapp.viewmodel.NotesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

object DialogBottomSheet {

    fun showDialogBottomSheet(scope: CoroutineScope,
        context: Context,
        listCategorias: MutableList<Categoria>,
        addCategory: (categoria: Categoria) -> Unit,
        getCategorias : suspend () -> MutableList<Categoria>
    ) {
        val inflater = LayoutInflater.from(context)
        val binding = BottomShetDialogBinding.inflate(inflater)
        val bottomSheetDialog = BottomSheetDialog(context)
        var selectedColor: Int? = null
        var selectedButton: ImageButton? = null // Variable para guardar el botón seleccionado
        val originalButtonColors = mutableMapOf<ImageButton, Int>() // Guarda los colores originales de los botones
        var listCategories : MutableList<Categoria> = listCategorias
        var colorS: Int
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
        val colorButtons: List<ImageButton> = listOf(
            binding.color1, binding.color2, binding.color3, binding.color4,
            binding.color5, binding.color6, binding.color7, binding.color8
        )

        // Asignar colores a los botones y manejar la selección

        fun getColor(color : Int){
            colorS= color
        }
        // Inicializar el adaptador para mostrar las categorías existentes
        @SuppressLint("NotifyDataSetChanged")
        fun initAdapter(list: MutableList<Categoria>) {
            val recycler = binding.svCategorias

            val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            recycler.layoutManager = layoutManager
            recycler.itemAnimator = null

            val adapter = CategoriesAdapter(list,{getColor(it)})
            recycler.adapter = adapter
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

            scope.launch {
                listCategories = getCategorias()
                initAdapter(listCategories)
            }
        }

        if (colorButtons.isNotEmpty() && colors.isNotEmpty()) {
            val size = minOf(colorButtons.size, colors.size) // Asegurarnos de que no haya desbordamiento de índices

            colorButtons.take(size).forEachIndexed { index, button ->
                val color = colors[index] // Obtener el color correspondiente
                button.backgroundTintList = ColorStateList.valueOf(color)

                button.setOnClickListener {
                    // Restaurar el color del botón previamente seleccionado
                    selectedButton?.setImageDrawable(null)
                    // Guardar el botón y color seleccionado
                    selectedButton = button
                    originalButtonColors[button] = color // Guardar el color original del botón
//                    button.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT) // Cambiar el color del botón seleccionado
                    button.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.check_ic))
                    selectedColor = color // Guardar el color seleccionado en la variable
                }
            }
        } else {
            // Manejar el caso si las listas están vacías o no coinciden en tamaño
            Toast.makeText(context, "Faltan colores o botones", Toast.LENGTH_SHORT).show()
        }

        // Mostrar las categorías existentes
        initAdapter(listCategories)
        bottomSheetDialog.show()
    }
}