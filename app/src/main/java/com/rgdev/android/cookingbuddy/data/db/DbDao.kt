package com.rgdev.android.cookingbuddy.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.IngredientWithRecipes
import com.rgdev.android.cookingbuddy.data.relations.RecipeIngredientCrossRef
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients

@Dao
interface DbDao {

    @Transaction
    @Query("SELECT * FROM recipe ORDER BY title ASC")
    fun getAllRecipes(): LiveData<List<Recipe>?>

    @Transaction
    @Query("SELECT * FROM recipe ORDER BY price ASC")
    fun getAllRecipesOrderedByPrice(): LiveData<List<Recipe>?>

    @Transaction
    @Query("SELECT * FROM recipe ORDER BY favourite DESC, title ASC, price ASC")
    fun getAllRecipesOrderedByFavourites(): LiveData<List<Recipe>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: Recipe): Long

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)

    @Transaction
    @Query("SELECT * FROM ingredient")
    fun getAllIngredientsWithRecipes(): LiveData<List<IngredientWithRecipes>?>

    @Transaction
    @Query("SELECT * FROM ingredient")
    fun getAllIngredients(): LiveData<List<Ingredient>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = :id")
    suspend fun getRecipeById(id: Long): RecipeWithIngredients

    @Transaction
    @Query("SELECT * FROM ingredient WHERE ingredientId = :ingredientId")
    suspend fun getIngredientById(ingredientId: Long): IngredientWithRecipes

    @Transaction
    @Query("SELECT * FROM recipe ORDER BY title")
    fun getRecipesWithIngredients(): LiveData<List<RecipeWithIngredients>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipeIngredientRelation(recipeIngredientCrossRef: RecipeIngredientCrossRef)
}