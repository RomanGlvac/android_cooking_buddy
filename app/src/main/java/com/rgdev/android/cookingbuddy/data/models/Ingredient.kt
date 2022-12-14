package com.rgdev.android.cookingbuddy.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "ingredient",
)
data class Ingredient(
    @PrimaryKey(autoGenerate = true) val ingredientId: Long = 0,
    val name: String,
    val price: Double
)
