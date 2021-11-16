package com.ameen.notekeeper.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ameen.notekeeper.R
import com.ameen.notekeeper.adapter.NoteAdapter
import com.ameen.notekeeper.data.local.NoteDataBase
import com.ameen.notekeeper.databinding.FragmentHomeNoteBinding
import com.ameen.notekeeper.repository.NoteRepository
import com.ameen.notekeeper.ui.BaseFragment
import com.ameen.notekeeper.viewmodel.NoteViewModel

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

class HomeNoteFragment : BaseFragment<FragmentHomeNoteBinding>(){

    private val TAG = "HomeNoteFragment"

    private lateinit var navController: NavController
    private lateinit var recyclerAdapter: NoteAdapter
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRepository: NoteRepository

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeNoteBinding
        get() = FragmentHomeNoteBinding::inflate

    override fun setupOnViewCreated(view: View) {
        navController = Navigation.findNavController(view)

        val db: NoteDataBase = NoteDataBase.getNoteDataBaseInstance(requireContext())
        noteRepository = NoteRepository(db)

        noteViewModel =
            ViewModelProvider(
                this,
                NoteViewModel.factory(noteRepository)
            )[NoteViewModel::class.java]

        recyclerAdapter = NoteAdapter()
        binding.notesRecyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        //noteViewModel = NoteViewModel(noteRepository)
        noteViewModel.notes.observe(this) { recyclerAdapter.diff.submitList(it) }

        binding.addNoteButton.setOnClickListener {
            navController.navigate(R.id.action_home_note_fragment_to_add_edit_fragment)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: Invoked!!")

        noteViewModel.getAllNotes()
        //noteViewModel.dummyData()
    }
}