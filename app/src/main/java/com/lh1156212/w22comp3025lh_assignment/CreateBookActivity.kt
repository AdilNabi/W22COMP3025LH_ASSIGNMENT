package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityCreateBookBinding

class CreateBookActivity : AppCompatActivity(), BookAdapter.BookItemListener {
    private lateinit var binding : ActivityCreateBookBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.createBookButton.setOnClickListener{
            var title = binding.titleNameEditText.text.toString().trim()
            var author = binding.authorEditText.text.toString().trim()
            var genre = binding.genreEditText.text.toString().trim()

            if(title.isNotEmpty() && author.isNotEmpty() && genre.isNotEmpty())
            {

                val db = FirebaseFirestore.getInstance().collection("books")
                val id = db.document().getId()

                var uID = auth.currentUser!!.uid

                var book = Book(title, author, genre, id, uID, ArrayList<Review>())

                db.document(id).set(book)
                    .addOnSuccessListener { Toast.makeText(this,"DB updated",Toast.LENGTH_LONG).show() }
                    .addOnFailureListener{ exception -> Log.w("DB_Issue", exception!!.localizedMessage)}
            }
            else
                Toast.makeText(this,"No field can be empty.", Toast.LENGTH_SHORT).show()

        }




        val viewModel : BookViewModel by viewModels()
        viewModel.getBooks().observe(this, { books ->
            binding.recyclerView.adapter = BookAdapter(this, books, this)

        })

    }

    override fun bookSelected(book: Book) {
        var intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra("bookID", book.id)
        startActivity(intent)
    }
}