package com.portafoliowebmariano.notasapp.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.portafoliowebmariano.notasapp.R
import com.portafoliowebmariano.notasapp.databinding.FragmentMainBinding
import com.portafoliowebmariano.notasapp.databinding.FragmentNotesBinding
import com.portafoliowebmariano.notasapp.model.NoteView
import com.portafoliowebmariano.notasapp.ui.Adapter.NotesViewAdapter
import com.portafoliowebmariano.notasapp.ui.dialog.DialogBottomSheet.showDialogBottomSheet
import com.portafoliowebmariano.notasapp.viewmodel.NotesViewModel


class NotesFragment () : Fragment() {
    private lateinit var listNotes: MutableList<NoteView>
    private lateinit var binding: FragmentNotesBinding
    private val notesViewModel: NotesViewModel by viewModels()
    private var order: Boolean = true


//    val slideUp = android.view.animation.AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val binding2 = FragmentMainBinding.bind(view)
//        val include = binding2.fmContainer
        initUI()
        controller()
        controllerNotes()
    }

    @SuppressLint("InflateParams")
    private fun controllerNotes() {
        binding.btnAddCategory.setOnClickListener {
            notesViewModel.showDialogBottomSheet(requireContext())
        }
    }


    private fun initUI() {
        observerListNotes()
    }

    private fun observerListNotes() {
        listNotes = mutableListOf()
        notesViewModel.getListNotesView()
        notesViewModel.listNotesView.observe(viewLifecycleOwner) { list ->
            listNotes = list
            initAdapter(list)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initAdapter(list: MutableList<NoteView>) {
        val recyler = binding.rvNotes
        val layoutManager = LinearLayoutManager(context)
        layoutManager.reverseLayout = order
        recyler.layoutManager = layoutManager
//        recyler.layoutManager = GridLayoutManager(context,2)
        recyler.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyler.itemAnimator = null

        val adapter = NotesViewAdapter(list) {
            deleteNotes(it)
        }

        recyler.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun deleteNotes(Note: NoteView) {
        notesViewModel.deleteNoteView(Note)
    }

    private fun controller() {


        binding.btnSettings.setOnClickListener {

            findNavController().navigate(R.id.action_notesFragment_to_settingsFragment)
        }

        binding.btnAddNote.setOnClickListener {
            notesViewModel.showDialogAddNoteView(requireContext())
        }
        binding.btnMoreOption.setOnClickListener {
            if (binding.btnAddNote.isVisible && binding.btnAddCategory.isVisible) {

                binding.btnAddNote.animate().alpha(0f).setDuration(300).withEndAction {
                    binding.btnAddNote.visibility = View.INVISIBLE
                }
                binding.tvAddNote.animate().alpha(0f).setDuration(300).withEndAction {
                    binding.tvAddNote.visibility = View.INVISIBLE
                }
                binding.tvAddCategory.animate().alpha(0f).setDuration(300).withEndAction {
                    binding.tvAddCategory.visibility = View.INVISIBLE
                }

                binding.btnAddCategory.animate().alpha(0f).setDuration(300).withEndAction {
                    binding.btnAddCategory.visibility = View.INVISIBLE
                }
            } else {
                binding.btnAddNote.visibility = View.VISIBLE
                binding.btnAddNote.alpha = 0f
                binding.btnAddNote.animate().alpha(1f).setDuration(300)
                binding.btnAddNote.animate().translationX(5f)

                binding.tvAddNote.visibility = View.VISIBLE
                binding.tvAddNote.alpha = 0f
                binding.tvAddNote.animate().alpha(1f).setDuration(300)
                binding.tvAddNote.animate().translationX(5f)

                binding.tvAddCategory.visibility = View.VISIBLE
                binding.tvAddCategory.alpha = 0f
                binding.tvAddCategory.animate().alpha(1f).setDuration(300)
                binding.tvAddCategory.animate().translationX(5f)

                binding.btnAddCategory.visibility = View.VISIBLE
                binding.btnAddCategory.alpha = 0f
                binding.btnAddCategory.animate().alpha(1f).setDuration(300)

            }
        }

        binding.btnCheck.setOnClickListener {
            findNavController().navigate(R.id.action_notesFragment_to_homeFragment)
            toggleButtonColors(binding.btnCheck, binding.btnNotas)
        }
    }
    @SuppressLint("UseCompatTextViewDrawableApis")
    private fun toggleButtonColors(selectedButton: ImageView, otherButton: ImageView) {
        val primaryColor = ContextCompat.getColor(requireContext(), R.color.color_primario)
        val secondaryColor = ContextCompat.getColor(requireContext(), R.color.ColorSecundario)
        val white = ContextCompat.getColor(requireContext(), R.color.white)



        // Cambiar color de fondo y texto
        selectedButton.setBackgroundColor(primaryColor)
//        selectedButton.setTextColor(secondaryColor)
//        selectedButton.compoundDrawableTintList = ColorStateList.valueOf(secondaryColor) // Cambiar drawableTint

        otherButton.setBackgroundColor(secondaryColor)
//        otherButton.setTextColor(primaryColor)
//        otherButton.compoundDrawableTintList = ColorStateList.valueOf(primaryColor) // Cambiar drawableTint
    }
}