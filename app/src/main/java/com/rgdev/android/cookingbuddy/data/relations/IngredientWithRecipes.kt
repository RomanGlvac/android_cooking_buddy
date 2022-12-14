package com.rgdev.android.cookingbuddy.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe

data class IngredientWithRecipes(
    @Embedded val ingredient: Ingredient,
    @Relation(
        parentColumn = "ingredientId",
        entityColumn = "recipeId",
        associateBy = Junction(RecipeIngredientCrossRef::class)
    ) val recipes: List<Recipe>?
)
