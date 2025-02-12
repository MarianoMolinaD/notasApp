package com.portafoliowebmariano.notasapp.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.core.content.ContextCompat
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.ActivityMainBinding
import com.portafoliowebmariano.notasapp.ui.fragment.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        window.statusBarColor = ContextCompat.getColor(this, R.color.color_primario)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fmContainerMain, MainFragment())
                .commit()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.let { controller ->
                // Cambiar el color de los íconos a oscuro (para fondos claros)
                controller.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
                // O cambiar el color de los íconos a claro (para fondos oscuros)
                controller.setSystemBarsAppearance(
                    0,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        }
    }
}