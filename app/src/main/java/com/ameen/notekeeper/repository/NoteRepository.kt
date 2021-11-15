package com.ameen.notekeeper.repository

import com.ameen.notekeeper.data.local.NoteDataBase

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */
class NoteRepository(private val db: NoteDataBase) {

    suspend fun getAllNotes() = db.noteDao()?.getAllNotes()

}