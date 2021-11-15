package com.ameen.notekeeper.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ameen.notekeeper.data.model.Note
import com.ameen.notekeeper.databinding.ItemNoteBinding

class NoteAdapter() :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val TAG = "NoteAdapter"

    inner class NoteViewHolder(val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var _binding: ItemNoteBinding? = null

    private val differCallBack = object : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        _binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val item = diff.currentList[position]

        holder.itemView.setOnLongClickListener(View.OnLongClickListener {
            Log.i(TAG, "onBindViewHolder: LongClick Pressed")
            return@OnLongClickListener true
        })

        if (item.title != null) {
            holder.binding.titleTextView.visibility = View.VISIBLE
            holder.binding.titleTextView.text = item.title
        }
        holder.binding.bodyTextView.text = item.noteBody
    }

    override fun getItemCount(): Int = diff.currentList.size

    private var onItemClickListener: ((Note) -> Unit)? = null
    fun onItemClicked(listener: (Note) -> Unit) {
        onItemClickListener = listener
    }
}