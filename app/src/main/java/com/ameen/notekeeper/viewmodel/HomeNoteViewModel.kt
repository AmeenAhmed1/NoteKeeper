package com.ameen.notekeeper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameen.notekeeper.data.model.Note
import com.ameen.notekeeper.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */
class HomeNoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var notes: MutableLiveData<List<Note>> = MutableLiveData()

    init {
        //getAllNotes()
        dummyData()
    }

    private fun getAllNotes() = viewModelScope.launch {
        val result = noteRepository.getAllNotes()
        notes.postValue(result!!)
    }

    private fun dummyData() {
        notes.postValue(
            listOf(
                Note(title = "Title 1", noteBody = "Body 1"),
                Note(title = "Title 2", noteBody = "Body 2"),
                Note(title = "Title 3", noteBody = "Body 3"),
                Note(noteBody = "Body For Null Title.")
            )
        )
    }
}