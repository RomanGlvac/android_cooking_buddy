package com.rgdev.android.cookingbuddy.data.repository

import androidx.lifecycle.LiveData
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.IngredientWithRecipes
import com.rgdev.android.cookingbuddy.data.relations.RecipeIngredientCrossRef
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients

interface DataRepository {

    // Recipes
    suspend fun addRecipe(recipe: Recipe): Long
    suspend fun updateRecipe(recipe: Recipe)
    suspend fun deleteRecipe(recipe: Recipe)
    suspend fun getRecipe(recipeId: Long): RecipeWithIngredients

    fun getRecipes(): LiveData<List<Recipe>?>
    fun getRecipesWithIngredients(): LiveData<List<RecipeWithIngredients>?>
    fun getAllRecipesOrderedByPrice(): LiveData<List<Recipe>?>
    fun getAllRecipesOrderedByFavourites(): LiveData<List<Recipe>?>

    // Ingredients
    suspend fun addIngredient(ingredient: Ingredient)
    suspend fun getIngredient(ingredientId: Long): IngredientWithRecipes

    fun getIngredients(): LiveData<List<Ingredient>?>
    fun getIngredientsWithRecipes(): LiveData<List<IngredientWithRecipes>?>

    // Mapping
    suspend fun insertRecipeIngredientCrossRef(recipeIngredientCrossRef: RecipeIngredientCrossRef)

}