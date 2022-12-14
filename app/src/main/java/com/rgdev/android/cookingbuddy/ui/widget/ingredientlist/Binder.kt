package com.rgdev.android.cookingbuddy.ui.widget.ingredientlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rgdev.android.cookingbuddy.data.models.Ingredient

@BindingAdapter("ingredientItems")
fun applyRecipeItems(recyclerView: RecyclerView, ingredients: List<Ingredient>?){
    val adapter = recyclerView.adapter as IngredientAdapter
    adapter.submitList(ingredients)
}