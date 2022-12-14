package com.rgdev.android.cookingbuddy.ui.viewmodels.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.RecipeIngredientCrossRef
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddRecipeViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    val ingredients = liveData {
        emitSource(dataRepository.getIngredients())
    }

    private val _recipeAdded = MutableLiveData(false)
    val recipeAdded: LiveData<Boolean>
        get() = _recipeAdded

    private val _addedIngredients = MutableLiveData(mutableSetOf<Ingredient>())
    val addedIngredients: LiveData<MutableSet<Ingredient>>
        get() = _addedIngredients

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            _recipeAdded.postValue(false)
            val recipeId = dataRepository.addRecipe(recipe)
            _addedIngredients.value?.forEach {
                dataRepository.insertRecipeIngredientCrossRef(
                    RecipeIngredientCrossRef(recipeId, it.ingredientId)
                )
            }
            _recipeAdded.postValue(true)
        }

    }

    fun addIngredient(ingredient: Ingredient) {
        if (!_addedIngredients.value?.add(ingredient)!!) {
            _addedIngredients.value?.remove(ingredient)
        }
    }

}