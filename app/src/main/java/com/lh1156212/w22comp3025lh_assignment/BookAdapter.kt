package com.lh1156212.w22comp3025lh_assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter (val context : Context,
                   val books : List<Book>,
                   val itemListener : BookItemListener
                    ): RecyclerView.Adapter<BookAdapter.BookViewHolder>(){





    inner class BookViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val bookTextView = itemView.findViewById<TextView>(R.id.bookTextView)
        val genreTextView = itemView.findViewById<TextView>(R.id.genreTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: BookViewHolder, position: Int) {
        val book = books[position]
        with(viewHolder){
            bookTextView.text = book.title
            genreTextView.text = book.genre
            itemView.setOnClickListener {
                itemListener.bookSelected(book)
            }
        }
    }


    override fun getItemCount(): Int {
        return books.size
    }


    interface BookItemListener{
        fun bookSelected(book : Book)
    }

}