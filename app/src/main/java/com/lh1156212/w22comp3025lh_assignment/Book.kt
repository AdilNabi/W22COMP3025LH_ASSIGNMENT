package com.lh1156212.w22comp3025lh_assignment

class Book (
    var title : String? = null,
    var author : String? = null,
    var genre : String? = null,
    var id : String? = null,
    var uID : String? = null,
    var reviewList : ArrayList<Review>? = null
        )

{
    override
    fun toString() : String{
        if (title != null)
            return title!!
        else
            return "error, undefined."
    }


}
