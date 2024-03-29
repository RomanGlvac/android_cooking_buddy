package com.rgdev.android.cookingbuddy.data.relations

import androidx.room.Entity

@Entity(
    primaryKeys = ["recipeId", "ingredientId"]
)
data class RecipeIngredientCrossRef(
    val recipeId: Long,
    val ingredientId: Long
)
