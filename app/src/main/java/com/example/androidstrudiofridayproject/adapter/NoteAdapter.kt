package com.example.androidstrudiofridayproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstrudiofridayproject.MainActivity
import com.example.androidstrudiofridayproject.R
import com.example.androidstrudiofridayproject.data.ItemDiffUtil
import com.example.androidstrudiofridayproject.data.NotesList
import com.example.androidstrudiofridayproject.data.model.Note
import com.example.androidstrudiofridayproject.databinding.NoteItemBinding

class NoteAdapter(
    private var context: Context,
private var dataset: List<Note>
) : ListAdapter<Note, NoteAdapter.ItemViewHolder>(ItemDiffUtil()) {

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: NoteItemBinding) : RecyclerView.ViewHolder(binding.root)

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //val note = dataset[position]
        val note = getItem(position)
        holder.binding.noteTitleTV.text = note.noteTitle
        holder.binding.noteBodyTV.text = note.noteBody


        holder.binding.bigListItem.setOnLongClickListener {

            var listOfNotes = NotesList().loadItems()
            AlertDialog.Builder(context)
                .setTitle("delete?")
                .setPositiveButton("yes"){_,_ ->
                   dataset = dataset - note
                    submitList(dataset)
                }
                .show()

            return@setOnLongClickListener true
        }
    }

    /**
     * damit der LayoutManager weiß, wie lang die Liste ist
     */
/*
    override fun getItemCount(): Int {
        return dataset.size
    }


 */

}