package com.rgdev.android.cookingbuddy.ui.widget.recipelist

import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients

interface RecipeActions {
    fun onClick(recipe: Recipe)
    fun onFavourite(recipe: Recipe)
    fun onDelete(recipe: Recipe)
}