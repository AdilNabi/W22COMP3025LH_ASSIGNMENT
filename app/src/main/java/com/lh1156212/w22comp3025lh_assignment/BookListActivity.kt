package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookListBinding

class BookListActivity : AppCompatActivity(), BookAdapter.BookItemListener {
    private lateinit var binding : ActivityBookListBinding
    private lateinit var auth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        binding.addBookButton.setOnClickListener{
            var intent = Intent(this, AddBookActivity::class.java)
            startActivity(intent)
        }


        val viewModel : BookViewModel by viewModels()
        viewModel.getBooks().observe(this) { books ->
            binding.recyclerView.adapter = BookAdapter(this, books, this)

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
//                startActivity(Intent(applicationContext, BookListActivity::class.java))
            return true
            }
            R.id.action_view_account -> {
                startActivity((Intent(application, ProfileActivity::class.java)))
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun bookSelected(book: Book) {
        var intent = Intent(this, BookDetailActivity::class.java)
        intent.putExtra("bookID", book.id)
        startActivity(intent)
    }
}