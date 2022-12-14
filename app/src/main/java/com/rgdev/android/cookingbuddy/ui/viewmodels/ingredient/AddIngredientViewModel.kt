package com.rgdev.android.cookingbuddy.ui.viewmodels.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddIngredientViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    fun addIngredient(ingredient: Ingredient) {
        viewModelScope.launch {
            dataRepository.addIngredient(ingredient)
        }
    }

}