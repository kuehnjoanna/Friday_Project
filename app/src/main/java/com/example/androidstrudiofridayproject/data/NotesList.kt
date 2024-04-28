package com.example.androidstrudiofridayproject.data

import com.example.androidstrudiofridayproject.data.model.Note

class NotesList {

    fun loadItems():  MutableList<Note>{
       return mutableListOf(
           Note("note1", "note body"),
       )
    }
}