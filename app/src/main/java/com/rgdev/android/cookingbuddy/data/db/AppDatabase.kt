package com.rgdev.android.cookingbuddy.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.relations.RecipeIngredientCrossRef

@Database(
    entities = [Recipe::class, Ingredient::class, RecipeIngredientCrossRef::class],
    version = 8,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dbDao(): DbDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "barsDatabase"
            ).fallbackToDestructiveMigration()
                .build()
    }
}