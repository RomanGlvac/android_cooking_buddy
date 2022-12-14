package com.rgdev.android.cookingbuddy.ui.viewmodels.recipe

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.ui.utils.RecipeSortType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    val recipes = MediatorLiveData<List<Recipe>?>()
    private val recipesByName = dataRepository.getRecipes()
    private val recipesByPrice = dataRepository.getAllRecipesOrderedByPrice()
    private val recipesByFavourites = dataRepository.getAllRecipesOrderedByFavourites()
    private var recipeSortType = RecipeSortType.NAME

    init {
        recipes.addSource(recipesByName){
            if (recipeSortType == RecipeSortType.NAME){
                recipes.postValue(it)
            }
        }

        recipes.addSource(recipesByFavourites){
            if (recipeSortType == RecipeSortType.FAVOURITE){
                recipes.postValue(it)
            }
        }

        recipes.addSource(recipesByPrice){
            if (recipeSortType == RecipeSortType.PRICE){
                recipes.postValue(it)
            }
        }
    }

    fun updateRecipe(recipe: Recipe){
        viewModelScope.launch {
            dataRepository.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch {
            dataRepository.deleteRecipe(recipe)
        }
    }

    fun setSortType(recipeSortType: RecipeSortType){
        when(recipeSortType){
            RecipeSortType.NAME -> recipesByName.value?.let { recipes.value = it }
            RecipeSortType.PRICE -> recipesByPrice.value?.let { recipes.value = it }
            RecipeSortType.FAVOURITE -> recipesByFavourites.value?.let { recipes.value = it }
        }.also {
            this.recipeSortType = recipeSortType
        }
    }

}