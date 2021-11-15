package com.ameen.notekeeper.data.local

import androidx.room.*
import com.ameen.notekeeper.data.model.Note

/**
 * Created by (Ameen Essa) on 11/13/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note: Note?)

    @Query("SELECT * FROM note_table")
    suspend fun getAllNotes(): List<Note>

    @Delete
    suspend fun deleteNote(note: Note?)
}