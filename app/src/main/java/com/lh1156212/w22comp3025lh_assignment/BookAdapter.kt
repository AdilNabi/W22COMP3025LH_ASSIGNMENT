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




    /**
     * This class is used to allow us to connect/access the elements in the
     * item_project layout file
     */
    inner class BookViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val bookTextView = itemView.findViewById<TextView>(R.id.bookTextView)
        val genreTextView = itemView.findViewById<TextView>(R.id.genreTextView)
    }

    /**
     * This connects (aka inflates) the individual ViewHolder (which is the link to the item_project.xml)
     * with the RecyclerView
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This method will bind the viewHolder with a specific project object
     */
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

    /**
     * This method returns the number of Projects in the recycler view
     */
    override fun getItemCount(): Int {
        return books.size
    }


    //In Java
    // public interface ProjectItemListener
    //{
    //  public projectSelected(Project project)
    // }

    interface BookItemListener{
        fun bookSelected(book : Book)
    }

}