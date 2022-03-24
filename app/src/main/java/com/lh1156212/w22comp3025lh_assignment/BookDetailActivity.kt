package com.lh1156212.w22comp3025lh_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookDetailBinding
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityCreateBookBinding

class BookDetailActivity : AppCompatActivity() {
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
                    binding.bookTextView.text = book.title
                    binding.linearLayout.removeAllViews()

                    for (review in book.reviewList!!)
                    {
                        var newBookTextView = TextView(this)
                        newBookTextView.text = review.title
                        binding.linearLayout.addView(newBookTextView)
                    }
                }

            }

        binding.createReviewButton.setOnClickListener{
            var title = binding.reviewTitleEditText.text.toString().trim()
            var reviewer = binding.reviewerEditText.text.toString().trim()
            var body = binding.reviewBodyEditText.text.toString().trim()

            if(title.isNotEmpty() && reviewer.isNotEmpty() && body.isNotEmpty())
            {
                var review = Review(title,reviewer,body)
                book.reviewList?.add(review)

                book?.let {
                    db.document(book.id!!).set(book)
                        .addOnSuccessListener { Toast.makeText(this,"Review Uploaded", Toast.LENGTH_LONG).show() }
                        .addOnFailureListener{Toast.makeText(this,"Review Uploaded Fail", Toast.LENGTH_LONG).show() }
                }

                binding.linearLayout.removeAllViews()
                for (review in book.reviewList!!)
                {
                    var newBookTextView = TextView(this)
                    newBookTextView.text = review.title
                    binding.linearLayout.addView(newBookTextView)
                }
            }
            else
                Toast.makeText(this,"All input fields are required", Toast.LENGTH_LONG).show()



        }




    }
}

