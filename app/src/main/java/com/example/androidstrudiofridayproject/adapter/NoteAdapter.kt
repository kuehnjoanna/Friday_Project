package com.example.androidstrudiofridayproject.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstrudiofridayproject.MainActivity
import com.example.androidstrudiofridayproject.OpenNoteActivity
import com.example.androidstrudiofridayproject.R
import com.example.androidstrudiofridayproject.data.ItemDiffUtil
import com.example.androidstrudiofridayproject.data.NotesList
import com.example.androidstrudiofridayproject.data.model.Note
import com.example.androidstrudiofridayproject.databinding.NoteItemBinding
import com.example.androidstrudiofridayproject.listOfNotes

class NoteAdapter(
    private var context: Context,
private var dataset: MutableList<Note>
) : ListAdapter<Note, NoteAdapter.ItemViewHolder>(ItemDiffUtil()) {

    private lateinit var mListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    /**
     * der ViewHolder umfasst die View uns stellt einen Listeneintrag dar
     */
    inner class ItemViewHolder(val binding: NoteItemBinding, listener: onItemClickListener) : RecyclerView.ViewHolder(binding.root){

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)


            }

        }


    }

    /**
     * hier werden neue ViewHolder erstellt
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //val note = dataset[position]
        val note = getItem(position)

        holder.binding.noteTitleTV.text = note.noteTitle
        holder.binding.noteBodyTV.text = note.noteBody

        holder.binding.bigListItem.setOnClickListener {
            val intent = Intent(context, OpenNoteActivity::class.java)
            context.startActivity(intent)
        }


        holder.binding.bigListItem.setOnLongClickListener {


            AlertDialog.Builder(context)
                .setTitle("delete?")
                .setIcon(R.drawable.delete)
                .setPositiveButton("yes"){_,_ ->

                 listOfNotes.removeAt(position)

                    notifyItemRemoved(position)
                    notifyDataSetChanged()
                    submitList(listOfNotes)
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