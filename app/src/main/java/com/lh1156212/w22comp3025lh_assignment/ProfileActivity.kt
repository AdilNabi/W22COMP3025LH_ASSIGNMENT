package com.lh1156212.w22comp3025lh_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProfileBinding
    private val authDb = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if(authDb.currentUser == null)
            logout()
        else
        {
            authDb.currentUser?.let {
                binding.userNametextView.text = it.displayName
                binding.emailTextView.text = it.email
            }
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
//                startActivity((Intent(application, ProfileActivity::class.java)))
                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }


    private fun logout()
    {
        authDb.signOut()
        finish()
        startActivity(Intent(this,SigninActivity::class.java))
    }

}