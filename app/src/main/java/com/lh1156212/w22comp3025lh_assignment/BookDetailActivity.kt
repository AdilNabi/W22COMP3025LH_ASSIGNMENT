package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookDetailBinding
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookListBinding
import java.util.logging.Logger.global

class BookDetailActivity : AppCompatActivity(), ReviewAdapter.ReviewItemListener {
    private lateinit var binding: ActivityBookDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bookID = intent.getStringExtra("bookID")
        var book = Book()


        val db = FirebaseFirestore.getInstance().collection("books")

        db.whereEqualTo("id", bookID)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot)
                {
                    book = document.toObject(Book::class.java)
                    binding.bookTitleTextView.text = book.title
                    binding.bookAuthorTextView.text = book.author
                    binding.bookGenreTextView.text = book.genre

//                    binding.linearLayout.removeAllViews()
//
//                    for (review in book.reviewList!!)
//                    {
//                        var newTitleTextView = TextView(this)
//                        newTitleTextView.text = "Title: " + review.title + " By: " + review.author
//                        binding.linearLayout.addView(newTitleTextView)
//
//                        var newBodyTextView = TextView(this)
//                        newBodyTextView.text = review.body + "\n"
//                        binding.linearLayout.addView(newBodyTextView)
//
//                    }
                }

            }

        binding.createReviewButton.setOnClickListener{
            var intent = Intent(this, AddReviewActivity::class.java)
            intent.putExtra("bookID", book.id)
            startActivity(intent)
        }



        var factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return  ReviewViewModel( bookID) as T
            }
        }


        val viewModel : ReviewViewModel by lazy {
            ViewModelProvider(this, factory).get(ReviewViewModel::class.java)
        }

        viewModel.getReview().observe(this) { reviews ->
            binding.recyclerView.adapter = ReviewAdapter(this, reviews, this)
        }


    }



    override fun reviewSelected(review: Review) {
        TODO("Not yet implemented")
    }

}

