package com.portafoliowebmariano.notasapp.ui.dialog

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.BottomShetDialogBinding
import com.portafoliowebmariano.notasapp.model.Categoria

object DialogBottomSheet {

    fun showDialogBottomSheet(context: Context, addCategory:(categoria : Categoria)-> Unit){
        val inflater = LayoutInflater.from(context)
        val binding = BottomShetDialogBinding.inflate(inflater)
        val bottomSheetDialog = BottomSheetDialog(context)
        var selectedColor: Int? = null
        bottomSheetDialog.setContentView(binding.root)


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

        val colorButtons: List<Button> = listOf(
            binding.color1, binding.color2, binding.color3, binding.color4,
            binding.color5, binding.color6, binding.color7, binding.color8
        )

        // Verifica que haya suficientes botones para los colores
        if (colorButtons.size >= colors.size) {
            colorButtons.forEachIndexed { index, button ->
                val color = colors[index] // Obtener el color correspondiente
                button.backgroundTintList = ColorStateList.valueOf(color)
                button.setOnClickListener {
                    selectedColor = color // Guardar el color en la variable
                }
            }
        }

        binding.ivSaveCategory.setOnClickListener{
            val color : Int? = selectedColor
            val nombre = binding.etCategory.text.toString().trim()

            var categoria = color?.let { it1 -> Categoria(nombreCategoria = nombre, color = it1) }
            if (categoria != null) {
                addCategory(categoria)
            }
        }

        bottomSheetDialog.show()
    }
}