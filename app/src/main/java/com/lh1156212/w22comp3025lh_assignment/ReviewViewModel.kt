package com.lh1156212.w22comp3025lh_assignment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.lh1156212.w22comp3025lh_assignment.databinding.ActivityBookDetailBinding

class ReviewViewModel(val bookID : String?) : ViewModel() {
    private val reviews = MutableLiveData<List<Review>>()

    init{

//        val bookID = "fNxpYZsBwXBp1E5gxMBt"

        val db = FirebaseFirestore.getInstance().collection("books")
            .whereEqualTo("id",bookID)
            .addSnapshotListener{documents, exception ->
                if (exception != null)
                {
                    Log.w("DB_Response", "Listen Failed ${exception.code}")
                    return@addSnapshotListener
                }

                documents?.let{
                    val reviewList = ArrayList<Review>()
                    for (document in documents)
                    {
                        Log.i("DB_Response", "${document.data}")
                        val book = document.toObject(Book::class.java)
                        for (review in book.reviewList!!)
                        {
                            reviewList.add(review)
                        }
                    }
                        reviews.value = reviewList
                }
            }
    }

    fun getReview() : LiveData<List<Review>>
    {
        return reviews
    }


}