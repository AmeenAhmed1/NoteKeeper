package com.ameen.notekeeper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ameen.notekeeper.data.model.Note
import com.ameen.notekeeper.repository.NoteRepository
import kotlinx.coroutines.launch

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */
class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {

    var notes: MutableLiveData<List<Note>> = MutableLiveData()

    fun getAllNotes() = viewModelScope.launch {
        val result = noteRepository.getAllNotes()
        notes.postValue(result!!)
    }

    fun insertOrUpdateNote(note: Note?) = viewModelScope.launch {
        noteRepository.insertOrUpdateNote(note)
    }

    companion object {
        fun factory(repository: NoteRepository): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewModel(repository) as T
                }
            }
        }
    }
}