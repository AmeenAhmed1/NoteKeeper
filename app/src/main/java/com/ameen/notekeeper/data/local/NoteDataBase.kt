package com.ameen.notekeeper.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ameen.notekeeper.data.model.Note

/**
 * Created by (Ameen Essa) on 11/13/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

@Database(entities = [Note::class], version = 1)
abstract class NoteDataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao?


    companion object {

        private var noteDataBaseInstance: NoteDataBase? = null

        @Synchronized
        fun getNoteDataBaseInstance(context: Application): NoteDataBase {

            if (noteDataBaseInstance == null) {
                noteDataBaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "NoteKeeper_DB.db"
                ).build()
            }

            return noteDataBaseInstance!!
        }
    }

}