package com.rgdev.android.cookingbuddy.ui.viewmodels.ingredient

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IngredientsViewModel @Inject constructor(
    dataRepository: DataRepository
) : ViewModel() {

    val ingredients = liveData {
        emitSource(dataRepository.getIngredients())
    }

}