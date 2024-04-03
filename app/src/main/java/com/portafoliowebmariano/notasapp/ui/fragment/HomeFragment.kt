package com.portafoliowebmariano.notasapp.ui.fragment

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.FragmentHomeBinding
import com.portafoliowebmariano.notasapp.model.Note
import com.portafoliowebmariano.notasapp.model.Setting
import com.portafoliowebmariano.notasapp.ui.Adapter.NotesAdapter
import com.portafoliowebmariano.notasapp.viewmodel.NotesViewModel
import java.text.SimpleDateFormat
import java.util.Date


class HomeFragment : Fragment() {

    private lateinit var listNotes: MutableList<Note>
    private lateinit var setting: Setting
    private lateinit var binding: FragmentHomeBinding

    private val notesViewModel: NotesViewModel by viewModels()

    private var order: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        contoller()
    }

    private fun initUI() {
        observerListNotes()
        observerSetting()
    }

    private fun observerSetting() {
        notesViewModel.getSetting()
        notesViewModel.setting.observe(viewLifecycleOwner) {
            try {
                setting = it
            } catch (e: Exception) {
                notesViewModel.addSetting(Setting(1, false))
                notesViewModel.getSetting()
            }
        }
    }

    private fun observerListNotes() {
        listNotes = mutableListOf()
        notesViewModel.getListNotes()
        notesViewModel.listNotes.observe(viewLifecycleOwner) { list ->
            listNotes = list
            initAdapter(list)
        }
    }

    private fun initAdapter(list: MutableList<Note>) {
        val recycler = binding.rvNotes
        val layoutManager = LinearLayoutManager(context)

        layoutManager.reverseLayout = order
        recycler.layoutManager = layoutManager
        val adapter = NotesAdapter(list, {
            confirmDelete(it)
        }, {
            updateNote(it)
        })
        recycler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun contoller() {
        binding.btnAddNote.setOnClickListener {
            notesViewModel.showDialogAddNote(requireContext())
        }
        binding.btnSettings.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
    }

    private fun deleteNote(note: Note) {
        notesViewModel.deleteNote(note)
    }

    private fun updateNote(note: Note) {
        notesViewModel.updateNote(note)
    }

    fun confirmDelete(note: Note) {
        if (setting.confirDelete) {
            notesViewModel.showDialogDeleteNote(requireContext(), note) {
                deleteNote(note)
            }
        } else {
            deleteNote(note)
        }
    }
}