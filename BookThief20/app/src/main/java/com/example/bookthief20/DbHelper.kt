package com.example.bookthief20

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "bookThiefDb", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INT PRIMARY KEY, login TEXT, email TEXT, pass TEXT)"
        db!!.execSQL(query)

        val bookTableQuery = "CREATE TABLE books (id INTEGER PRIMARY KEY, user_id INTEGER, image TEXT, title TEXT, " +
                "description TEXT, is_given INTEGER, FOREIGN KEY(user_id) REFERENCES users(id))"
        db.execSQL(bookTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS users")
        db.execSQL("DROP TABLE IF EXISTS books")
        onCreate(db)
    }

    fun addUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("pass", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)

        db.close()
    }

    fun getUser(login: String, pass: String):Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM users WHERE login = '$login' AND pass = '$pass'", null)
        return result.moveToFirst()
    }

    @SuppressLint("Range")
    fun getUserBooks(userId: Int): List<Book> {
        val bookList = mutableListOf<Book>()
        val db = this.readableDatabase

        val query = "SELECT * FROM books WHERE user_id = $userId"
        val cursor: Cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndex("id"))
                val image = cursor.getString(cursor.getColumnIndex("image"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val desc = cursor.getString(cursor.getColumnIndex("desc"))
                val isGiven = cursor.getInt(cursor.getColumnIndex("is_given")) == 1

                val book = Book(id, userId, image, title, desc, isGiven)
                bookList.add(book)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return bookList
    }

    fun addBook(book: Book) {
        val values = ContentValues()
        values.put("user_id", book.userId)
        values.put("image", book.image)
        values.put("title", book.title)
        values.put("desc", book.description)
        values.put("is_given", if (book.isGiven) 1 else 0)

        val db = this.writableDatabase
        db.insert("books", null, values)
        db.close()
    }
}