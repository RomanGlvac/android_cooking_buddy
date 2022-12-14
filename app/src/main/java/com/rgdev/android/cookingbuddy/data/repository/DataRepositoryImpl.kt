package com.rgdev.android.cookingbuddy.data.repository

import androidx.lifecycle.LiveData
import com.rgdev.android.cookingbuddy.data.db.DbDao
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.IngredientWithRecipes
import com.rgdev.android.cookingbuddy.data.relations.RecipeIngredientCrossRef
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients

class DataRepositoryImpl(private val dbDao: DbDao) : DataRepository {

    override suspend fun addRecipe(recipe: Recipe): Long {
        return dbDao.addRecipe(recipe)
    }

    override suspend fun addIngredient(ingredient: Ingredient){
        dbDao.addIngredient(ingredient)
    }

    override fun getRecipes(): LiveData<List<Recipe>?> {
        return dbDao.getAllRecipes()
    }

    override fun getRecipesWithIngredients(): LiveData<List<RecipeWithIngredients>?> {
        return dbDao.getRecipesWithIngredients()
    }

    override fun getAllRecipesOrderedByPrice(): LiveData<List<Recipe>?> {
        return dbDao.getAllRecipesOrderedByPrice()
    }

    override fun getAllRecipesOrderedByFavourites(): LiveData<List<Recipe>?> {
        return dbDao.getAllRecipesOrderedByFavourites()
    }

    //    override fun getIngredients(): LiveData<List<Ingredient>?> {
//        return dbDao.getAllIngredients()
//    }

    override fun getIngredients(): LiveData<List<Ingredient>?> {
        return dbDao.getAllIngredients()
    }

    override fun getIngredientsWithRecipes(): LiveData<List<IngredientWithRecipes>?> {
        return dbDao.getAllIngredientsWithRecipes()
    }

    //    override suspend fun getRecipe(recipeId: Long) : Recipe {
//        return dbDao.getRecipeById(recipeId)
//    }
    override suspend fun getRecipe(recipeId: Long) : RecipeWithIngredients {
        return dbDao.getRecipeById(recipeId)
    }

    override suspend fun getIngredient(ingredientId: Long): IngredientWithRecipes {
        return dbDao.getIngredientById(ingredientId)
    }

    override suspend fun updateRecipe(recipe: Recipe) {
        dbDao.updateRecipe(recipe)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        dbDao.deleteRecipe(recipe)
    }

    override suspend fun insertRecipeIngredientCrossRef(recipeIngredientCrossRef: RecipeIngredientCrossRef) {
        dbDao.insertRecipeIngredientRelation(recipeIngredientCrossRef)
    }
}