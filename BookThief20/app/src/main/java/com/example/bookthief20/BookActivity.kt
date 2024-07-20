package com.example.bookthief20

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val title: TextView = findViewById(R.id.book_info_title)
        val desc: TextView = findViewById(R.id.book_info_description)
        val button: Button = findViewById(R.id.button_back)

        button.setOnClickListener {
            val intent = Intent(this, LibraryActivity::class.java)
            startActivity(intent)
        }

        title.text = intent.getStringExtra("bookTitle")
        desc.text = intent.getStringExtra("bookDesc")
    }
}