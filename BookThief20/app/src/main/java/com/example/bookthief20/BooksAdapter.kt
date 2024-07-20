package com.example.bookthief20

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BooksAdapter(var books: List<Book>, var context: Context) : RecyclerView.Adapter<BooksAdapter.MyViewHolder>() {

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val image: ImageView = view.findViewById(R.id.book_list_image)
        val title: TextView = view.findViewById(R.id.book_list_title)
        val desc: TextView = view.findViewById(R.id.book_list_description)
        val btn: Button = view.findViewById(R.id.book_list_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_in_library, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = books[position].title
        holder.desc.text = books[position].description

        val imageId = context.resources.getIdentifier(
            books[position].image,
            "drawable",
            context.packageName
        )

        holder.image.setImageResource(imageId)

        holder.btn.setOnClickListener {
            val intent = Intent(context, BookActivity::class.java)

            intent.putExtra("bookTitle", books[position].title)
            intent.putExtra("bookDesc", books[position].description)
            intent.putExtra("bookImg", books[position].image)

            context.startActivity(intent)
        }

    }
}