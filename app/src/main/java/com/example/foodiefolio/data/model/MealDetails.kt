package com.example.foodiefolio.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "mealDetails")
data class MealDetails(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("strMeal")
    val name: String,
    @SerializedName("strMealThumb")
    val Img: String,
    @SerializedName("strCategory")
    val category: String,
    @SerializedName("strArea")
    val area: String,
    @SerializedName("strInstructions")
    val instructions: String,
    @SerializedName("strTags")
    val tags: String,
    @SerializedName("strYoutube")
    val youtube: String,
    @SerializedName("strIngredient1")
    val ingredient1: String,
    @SerializedName("strIngredient2")
    val ingredient2: String,
    @SerializedName("strIngredient3")
    val ingredient3: String,
    @SerializedName("strIngredient4")
    val ingredient4: String,
    @SerializedName("strIngredient5")
    val ingredient5: String,

    val fav: String
)
