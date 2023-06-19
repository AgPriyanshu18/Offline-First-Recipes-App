package com.example.foodiefolio.data.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryThumb")
    val Img: String,

    val fav: String
)
