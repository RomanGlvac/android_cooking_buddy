package com.rgdev.android.cookingbuddy.ui.widget.recipelist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients

@BindingAdapter("recipeItems")
fun applyRecipeItems(recyclerView: RecyclerView, recipes: List<Recipe>?){
    val adapter = recyclerView.adapter as RecipesAdapter
    adapter.submitList(recipes)
}