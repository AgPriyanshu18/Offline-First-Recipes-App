package com.example.foodiefolio.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meals")
data class Meals(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("idMeal")
    val id : String,
    @SerializedName("strMeal")
    val name : String,
    @SerializedName("strMealThumb")
    val Img : String,

    var category : String
)
