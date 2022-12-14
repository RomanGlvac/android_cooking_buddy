package com.rgdev.android.cookingbuddy.ui.viewmodels.recipe

import androidx.lifecycle.*
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.RecipeWithIngredients
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(private val dataRepository: DataRepository) :
    ViewModel() {

    val recipe = MutableLiveData<RecipeWithIngredients>()

    fun loadRecipe(id: Long) {
        viewModelScope.launch {
            recipe.postValue(dataRepository.getRecipe(id))
        }
    }

    val recipesWithIngredients = liveData {
        emitSource(dataRepository.getRecipesWithIngredients())
    }

    fun addFavouriteRecipe(recipe: Recipe?) {
        if (recipe == null) {
            return
        }
        recipe.favourite = !recipe.favourite
        viewModelScope.launch {
            dataRepository.updateRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe?) {
        if (recipe == null) {
            return
        }
        viewModelScope.launch {
            dataRepository.deleteRecipe(recipe)
        }
    }

}