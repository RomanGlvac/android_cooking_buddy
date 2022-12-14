package com.rgdev.android.cookingbuddy.ui.viewmodels.ingredient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import com.rgdev.android.cookingbuddy.data.relations.IngredientWithRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IngredientDetailViewModel @Inject constructor(
    private val dataRepository: DataRepository
) : ViewModel() {

    private val _ingredient = MutableLiveData<IngredientWithRecipes>()
    val ingredient: LiveData<IngredientWithRecipes>
        get() = _ingredient

    fun loadIngredient(id: Long){
        viewModelScope.launch {
            _ingredient.postValue(dataRepository.getIngredient(id))
        }
    }

}