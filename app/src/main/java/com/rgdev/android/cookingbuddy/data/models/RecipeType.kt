package com.rgdev.android.cookingbuddy.data.models

enum class RecipeType(val typeName: String) {

    MAIN_DISH("Main dish"), SOUP("Soup"), SWEETS("Sweets"), BAKING("Baking"), GENERAL("General");

    companion object {
        private val map = RecipeType.values().associateBy(RecipeType::typeName)
        fun fromString(type: String) = map[type]
    }
}