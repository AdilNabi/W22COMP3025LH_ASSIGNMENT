package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityMainBinding
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityProfileBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bookListButton.setOnClickListener{
            var intent = Intent(this, BookListActivity::class.java)
            startActivity(intent)
        }

        binding.createBookButton.setOnClickListener{
            var intent = Intent(this, AddBookActivity::class.java)
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