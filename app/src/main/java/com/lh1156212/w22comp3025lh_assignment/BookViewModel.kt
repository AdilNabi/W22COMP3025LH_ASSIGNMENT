package com.lh1156212.w22comp3025lh_assignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class BookViewModel : ViewModel() {
    private val books = MutableLiveData<List<Book>>()



    init{
        val userID = Firebase.auth.currentUser?.uid


        val db = FirebaseFirestore.getInstance().collection("books")
//            .whereEqualTo("uid",userID)
            .orderBy("title")
            .addSnapshotListener{ documents, exception ->
                if (exception != null)
                {
                    Log.w("DB_Response", "Listen Failed ${exception.code}")
                    return@addSnapshotListener
                }

                documents?.let{
                    val bookList = ArrayList<Book>()
                    for (document in documents)
                    {
                        Log.i("DB_Response", "${document.data}")
                        val book = document.toObject(Book::class.java)
                        bookList.add(book)
                    }
                    books.value = bookList
                }

            }
    }


    fun getBooks() : LiveData<List<Book>>
    {
        return books
    }
}