package com.lh1156212.w22comp3025lh_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityCreateBookBinding

class CreateBookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCreateBookBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createBookButton.setOnClickListener{
            var title = binding.titleNameEditText.text.toString().trim()
            var author = binding.authorEditText.text.toString().trim()
            var genre = binding.genreEditText.text.toString().trim()

            if(title.isNotEmpty() && author.isNotEmpty() && genre.isNotEmpty())
            {
                var book = Book(title, author, genre)
                val db = FirebaseFirestore.getInstance().collection("books")
                val id = db.document().getId()
                book.id = id

                db.document(id).set(book)
                    .addOnSuccessListener { Toast.makeText(this,"DB updated",Toast.LENGTH_LONG).show() }
                    .addOnFailureListener{ exception -> Log.w("DB_Issue", exception.localizedMessage)}
            }
            else
                Toast.makeText(this,"No field can be empty.", Toast.LENGTH_SHORT).show()

        }
    }
}