package com.example.androidstrudiofridayproject

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidstrudiofridayproject.data.model.Note
import com.example.androidstrudiofridayproject.databinding.ActivityOpenNoteBinding

class OpenNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpenNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOpenNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.openNoteTitleTV.text

        val text = intent.extras?.getString("Text")
        binding.ivAccount.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }

        binding.openNoteShareBTN.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, text)
            intent.type = "text/plain"
            val shareIntent = Intent.createChooser(intent, "test")
            startActivity(shareIntent)
        }
    }
}