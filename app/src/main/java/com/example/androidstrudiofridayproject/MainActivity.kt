package com.example.androidstrudiofridayproject

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import com.example.androidstrudiofridayproject.adapter.NoteAdapter
import com.example.androidstrudiofridayproject.data.NotesList
import com.example.androidstrudiofridayproject.data.model.Note
import com.example.androidstrudiofridayproject.databinding.ActivityMainBinding
import com.example.androidstrudiofridayproject.databinding.DialogAddNoteBinding
import com.example.androidstrudiofridayproject.databinding.NoteItemBinding
import java.lang.IllegalStateException
var listOfNotes = NotesList().loadItems().toMutableList()
class MainActivity : AppCompatActivity() {

  //  var listOfNotes = NotesList().loadItems()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // recyclerView von Layout wird mit code verknüpft
        val recyclerView = binding.recyclerView

        adapter = NoteAdapter(this, listOfNotes, )
        //adding list to a view (?!)
        adapter.submitList(listOfNotes)
        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : NoteAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

               listOfNotes.removeAt(position)
                adapter.notifyItemRemoved(position)
Toast.makeText(this@MainActivity, "clicked! on $position", Toast.LENGTH_SHORT).show()
            }
        }
        )




      showAddNoteDialog()


    }
    fun View.slideup(animation: Long, startOffSet: Long){
        val slideup = AnimationUtils.loadAnimation(context, R.anim.slide_up).apply {
            duration = animation
            interpolator = FastOutSlowInInterpolator()
            this.startOffset = startOffSet
        }
        startAnimation(slideup)
    }


    private fun showAddNoteDialog(){
        binding.fabBTN.setOnClickListener {
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_add_note, null)
            val editTitle = dialogLayout.findViewById<EditText>(R.id.newNoteTitleET)
            val editBody = dialogLayout.findViewById<EditText>(R.id.newNoteBodyET)

            AlertDialog.Builder(this)
                .setView(dialogLayout)
                .setPositiveButton("Add"){_,_ ->
                    val newNote = Note(editTitle.text.toString(), editBody.text.toString())
                    listOfNotes.add(newNote)
                    Log.d("list", "$listOfNotes")
                    adapter.submitList(listOfNotes)
                }
                .setNegativeButton("Cancel"){_,_ ->
                    Log.d("cancel note", "note adding was canceled")
                }
                .show()




        }


    }

}