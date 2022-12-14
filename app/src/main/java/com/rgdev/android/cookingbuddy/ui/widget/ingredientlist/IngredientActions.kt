package com.rgdev.android.cookingbuddy.ui.widget.ingredientlist

import com.rgdev.android.cookingbuddy.data.models.Ingredient

interface IngredientActions {
    fun onClick(ingredientId: Long) {}
    fun onIngredientAdd(ingredient: Ingredient) {}
}