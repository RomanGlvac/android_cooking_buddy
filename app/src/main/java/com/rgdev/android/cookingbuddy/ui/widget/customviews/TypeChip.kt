package com.rgdev.android.cookingbuddy.ui.widget.customviews

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.google.android.material.chip.Chip
import com.rgdev.android.cookingbuddy.R
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.models.RecipeType
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients

class TypeChip : Chip {

    constructor(context: Context): super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

}

@BindingAdapter("recipe")
fun TypeChip.applyRecipe(recipe: Recipe?){
    this.apply {
        chipIcon = when(recipe?.type){
            RecipeType.MAIN_DISH -> AppCompatResources.getDrawable(context, R.drawable.icon_main_dish)
            RecipeType.SOUP -> AppCompatResources.getDrawable(context, R.drawable.icon_soup)
            RecipeType.SWEETS -> AppCompatResources.getDrawable(context, R.drawable.icon_sweets)
            RecipeType.BAKING -> AppCompatResources.getDrawable(context, R.drawable.icon_baking)
            RecipeType.GENERAL -> AppCompatResources.getDrawable(context, R.drawable.icon_general)
            else -> AppCompatResources.getDrawable(context, R.drawable.icon_general)
        }
        text = recipe?.type?.typeName ?: "Unknown"
    }
}