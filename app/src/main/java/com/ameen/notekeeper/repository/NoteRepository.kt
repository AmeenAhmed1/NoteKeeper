package com.ameen.notekeeper.repository

import com.ameen.notekeeper.data.local.NoteDataBase
import com.ameen.notekeeper.data.model.Note

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */
class NoteRepository(private val db: NoteDataBase) {

    suspend fun getAllNotes() = db.noteDao()?.getAllNotes()

    suspend fun insertOrUpdateNote(note: Note?) = db.noteDao()?.saveNote(note)

    suspend fun deleteNote(note: Note?) = db.noteDao()?.deleteNote(note)
}