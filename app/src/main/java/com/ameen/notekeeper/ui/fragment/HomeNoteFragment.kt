package com.ameen.notekeeper.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.ameen.notekeeper.adapter.NoteAdapter
import com.ameen.notekeeper.data.local.NoteDataBase
import com.ameen.notekeeper.data.model.Note
import com.ameen.notekeeper.databinding.FragmentHomeNoteBinding
import com.ameen.notekeeper.repository.NoteRepository
import com.ameen.notekeeper.ui.BaseFragment
import com.ameen.notekeeper.ui.MainActivity
import com.ameen.notekeeper.viewmodel.HomeNoteViewModel

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

class HomeNoteFragment : BaseFragment<FragmentHomeNoteBinding>() {

    private lateinit var navController: NavController
    private lateinit var recyclerAdapter: NoteAdapter
    private lateinit var homeNoteViewModel: HomeNoteViewModel
    private lateinit var noteRepository: NoteRepository

    private lateinit var notes: List<Note>

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeNoteBinding
        get() = FragmentHomeNoteBinding::inflate

    override fun setupOnViewCreated(view: View) {
        navController = Navigation.findNavController(view)

        val db: NoteDataBase = NoteDataBase.getNoteDataBaseInstance(requireContext())
        noteRepository = NoteRepository(db)

        homeNoteViewModel = HomeNoteViewModel(noteRepository)
        homeNoteViewModel.notes.observe(this, Observer {
            recyclerAdapter.diff.submitList(it)
        })

        recyclerAdapter = NoteAdapter()
        binding.notesRecyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

}