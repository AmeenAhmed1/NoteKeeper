package com.ameen.notekeeper.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by (Ameen Essa) on 11/13/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

@Entity(tableName = "Note_Table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String? = null,
    val noteBody: String? = null
)
