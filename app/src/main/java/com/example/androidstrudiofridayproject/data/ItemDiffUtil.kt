package com.example.androidstrudiofridayproject.data

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import com.example.androidstrudiofridayproject.data.model.Note

class ItemDiffUtil: ItemCallback<Note>(){

    //Entscheidet ob zwei Items das gleiche Item sind
    //Normalerweise kann man dazu ids vergleichen o.Ä.
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        val result = oldItem.noteTitle == newItem.noteTitle
        Log.d("areItemsTheSame", "result: $result oldItem: $oldItem, newItem: $newItem")
        return result
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        val result = oldItem.noteBody == newItem.noteBody
        Log.d("areItemsTheSame", "result: $result oldItem: $oldItem, newItem: $newItem")
        return result
    }
}