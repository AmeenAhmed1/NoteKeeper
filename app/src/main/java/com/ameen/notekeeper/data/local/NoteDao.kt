package com.ameen.notekeeper.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ameen.notekeeper.data.model.Note

/**
 * Created by (Ameen Essa) on 11/13/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

@Dao
interface NoteDao {

    @Insert
    fun saveNote(note: Note?)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): List<Note>
}