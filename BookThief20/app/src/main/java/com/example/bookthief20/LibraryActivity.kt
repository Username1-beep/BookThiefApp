package com.example.bookthief20

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LibraryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        val booksList: RecyclerView = findViewById(R.id.books_list)
        val books = arrayListOf<Book>()

        books.add(Book(1, 1,"cover", "Обитаемый остров", "Аркадий и Борис Стругацкие, 2016", false ))
        books.add(Book(2, 1,"cover2", "Пикник на обочине", "Аркадий и Борис Стругацкие, 2008", false ))
        books.add(Book(3, 1,"cover3", "Малыш", "Аркадий и Борис Стругацкие, 2012", false ))
        books.add(Book(4, 1,"cover4", "Хищные вещи века", "Аркадий и Борис Стругацкие, 2016", false ))

        booksList.layoutManager = LinearLayoutManager(this)
        booksList.adapter = BooksAdapter(books, this)
    }
}