package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityAddReviewBinding
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookDetailBinding

class AddReviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val bookID = intent.getStringExtra("bookID")
        var book = Book()
        val db = FirebaseFirestore.getInstance().collection("books")

        db.whereEqualTo("id", bookID)
            .get()
            .addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    book = document.toObject(Book::class.java)
                }

            }


        binding.createReviewButton.setOnClickListener {
            var title = binding.reviewTitleEditText.text.toString().trim()
            var reviewer = binding.reviewerEditText.text.toString().trim()
            var body = binding.reviewBodyEditText.text.toString().trim()

            if (title.isNotEmpty() && reviewer.isNotEmpty() && body.isNotEmpty()) {
                var review = Review(title, reviewer, body)
                book.reviewList?.add(review)

                book?.let {
                    db.document(book.id!!).set(book)
                        .addOnSuccessListener {
                            Toast.makeText(
                                this,
                                "Review Uploaded",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                        .addOnFailureListener {
                            Toast.makeText(
                                this,
                                "Review Uploaded Fail",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            } else
                Toast.makeText(this, "All input fields are required", Toast.LENGTH_LONG).show()


        }

        binding.backButton.setOnClickListener{
            finish()
        }

        binding.homeButton.setOnClickListener{
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        setSupportActionBar(binding.mainToolBar.toolbar)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add_book -> {
                startActivity(Intent(applicationContext, AddBookActivity::class.java))
                return true
            }
            R.id.action_view_book -> {
                startActivity(Intent(applicationContext, BookListActivity::class.java))
                return true
            }
            R.id.action_view_account -> {
                startActivity((Intent(application, ProfileActivity::class.java)))
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}