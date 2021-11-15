package com.ameen.notekeeper.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ameen.notekeeper.data.local.NoteDataBase
import com.ameen.notekeeper.databinding.FragmentAddEditNoteBinding
import com.ameen.notekeeper.ui.BaseFragment

/**
 * Created by (Ameen Essa) on 11/15/2021
 * Copyright (c) 2021 . All rights reserved.
 * @Ameen.MobileDev@gmail.com
 */

class AddEditFragment : BaseFragment<FragmentAddEditNoteBinding>() {

    private lateinit var appDataBase: NoteDataBase

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddEditNoteBinding
        get() = FragmentAddEditNoteBinding::inflate

    override fun setupOnViewCreated(view: View) {

        appDataBase = NoteDataBase.getNoteDataBaseInstance(requireContext())
    }
}