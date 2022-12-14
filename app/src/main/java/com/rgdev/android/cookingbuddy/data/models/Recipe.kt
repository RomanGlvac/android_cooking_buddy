package com.rgdev.android.cookingbuddy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe")
data class Recipe(
    @PrimaryKey(autoGenerate = true) val recipeId: Long = 0,
    val title: String,
    val recipeText: String,
    val price: Double = 0.0,
    val type: RecipeType?,
    var favourite: Boolean
)
