package com.rgdev.android.cookingbuddy.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe

data class RecipeWithIngredients(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = "recipeId",
        entityColumn = "ingredientId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    ) val ingredients: List<Ingredient>?
)
