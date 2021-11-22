package com.ameen.notekeeper.ui.fragment

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ameen.notekeeper.data.local.NoteDataBase
import com.ameen.notekeeper.data.model.Note
import com.ameen.notekeeper.databinding.FragmentAddEditNoteBinding
import com.ameen.notekeeper.repository.NoteRepository
import com.ameen.notekeeper.ui.BaseFragment
import com.ameen.notekeeper.viewmodel.NoteViewModel

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

class AddEditFragment : BaseFragment<FragmentAddEditNoteBinding>() {

    private val TAG = "AddEditFragment"

    private lateinit var noteViewModel: NoteViewModel
    private lateinit var noteRepository: NoteRepository

    private val args: AddEditFragmentArgs by navArgs()
    private lateinit var selectedNoteDetails: Note

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddEditNoteBinding
        get() = FragmentAddEditNoteBinding::inflate

    override fun setupOnViewCreated(view: View) {

        val db: NoteDataBase = NoteDataBase.getNoteDataBaseInstance(requireContext())
        noteRepository = NoteRepository(db)

        noteViewModel =
            ViewModelProvider(
                this,
                NoteViewModel.factory(noteRepository)
            )[NoteViewModel::class.java]

        Log.i(TAG, "setupOnViewCreated: isAddNote --> ${args.isAddNote}")
        Log.i(TAG, "setupOnViewCreated: SelectedNote --> ${args.selectedNote}")

        if (!args.isAddNote) {
            selectedNoteDetails = args.selectedNote!!
            initView()
        }

        binding.addNoteButton.setOnClickListener {
            saveNote()
        }

        binding.deleteNoteButton.setOnClickListener {
            deleteNote(selectedNoteDetails)
        }
    }

    private fun initView() {

        Log.i(TAG, "initView: Args --> ${args.selectedNote}")

        binding.deleteNoteButton.visibility = View.VISIBLE
        binding.titleNoteEditText.setText(selectedNoteDetails.title)
        binding.bodyNoteEditText.setText(selectedNoteDetails.noteBody)

    }


    private fun deleteNote(note: Note?) {
        noteViewModel.deleteNote(note)
        findNavController().popBackStack()
    }

    private fun saveNote() {

        Log.i(TAG, "saveNote: Title --> ${binding.titleNoteEditText.text}")
        Log.i(TAG, "saveNote: Body --> ${binding.bodyNoteEditText.text}")

        val title = binding.titleNoteEditText.text.toString()
        val body = binding.bodyNoteEditText.text.toString()

        if (!args.isAddNote) {
            noteViewModel.insertOrUpdateNote(
                selectedNoteDetails.copy(
                    title = title,
                    noteBody = body
                )
            )
            findNavController().popBackStack()
        } else {
            if (body != "") {
                if (title == "")
                    noteViewModel.insertOrUpdateNote(Note(noteBody = body))
                else
                    noteViewModel.insertOrUpdateNote(Note(title = title, noteBody = body))
                findNavController().popBackStack()
            } else
                Toast.makeText(requireContext(), "Note body can`t be empty!!", Toast.LENGTH_SHORT)
                    .show()
        }
    }
}