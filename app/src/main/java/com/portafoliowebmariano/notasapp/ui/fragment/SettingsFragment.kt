package com.portafoliowebmariano.notasapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.FragmentSettingsBinding
import com.portafoliowebmariano.notasapp.model.Setting
import com.portafoliowebmariano.notasapp.viewmodel.NotesViewModel

class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private val notesViewModel : NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        controller()
        observer()

    }

    private fun observer() {
        notesViewModel.setting.observe(viewLifecycleOwner){
            try {
                binding.cbDelete.isChecked = it.confirDelete
            }catch (e:Exception){
                notesViewModel.addSetting(Setting(1,false))
            }
        }
    }

    private fun initUI() {
        notesViewModel.getSetting()
    }

    private fun controller() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cbDelete.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                notesViewModel.updateSetting(Setting(1,true))
            }else{
                notesViewModel.updateSetting(Setting(1,false))
            }

        }
    }
}