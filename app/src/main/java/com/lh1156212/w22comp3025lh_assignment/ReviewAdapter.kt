package com.lh1156212.w22comp3025lh_assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ReviewAdapter (val context : Context,
                    val reviews : List<Review>,
                    val itemListener: ReviewItemListener
                    ): RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>(){



    inner class ReviewViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleTextView = itemView.findViewById<TextView>(R.id.bookTextView)
        val nameTextView = itemView.findViewById<TextView>(R.id.genreTextView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return ReviewViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ReviewViewHolder, position: Int) {
        val review = reviews[position]
        with(viewHolder){
            titleTextView.text = review.title
            nameTextView.text = review.author
            itemView.setOnClickListener {
                itemListener.reviewSelected(review)
            }
        }
    }




    override fun getItemCount(): Int {
        return reviews.size
    }



    interface ReviewItemListener{
        fun reviewSelected(review : Review)
    }


    }