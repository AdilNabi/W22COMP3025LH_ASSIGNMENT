package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityAddBookBinding
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookListBinding

class AddBookActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddBookBinding
    private lateinit var auth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddBookBinding.inflate(layoutInflater)
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
//                startActivity(Intent(applicationContext, AddBookActivity::class.java))
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