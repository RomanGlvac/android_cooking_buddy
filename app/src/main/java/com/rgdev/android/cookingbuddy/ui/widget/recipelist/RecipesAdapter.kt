package com.rgdev.android.cookingbuddy.ui.widget.recipelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rgdev.android.cookingbuddy.R
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.databinding.IngredientRecipeItemBinding
import com.rgdev.android.cookingbuddy.databinding.RecipeItemBinding
import com.rgdev.android.cookingbuddy.ui.utils.createPriceString
import com.rgdev.android.cookingbuddy.ui.widget.customviews.applyRecipe
import com.rgdev.android.cookingbuddy.ui.widget.utils.IngredientViewHolderType
import com.rgdev.android.cookingbuddy.ui.widget.utils.RecipeViewHolderType

class RecipesAdapter(
    private val recipeViewHolderType: RecipeViewHolderType,
    private val actions: RecipeActions?
) :
    ListAdapter<Recipe, RecipesAdapter.RecipeViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.recipeId == newItem.recipeId
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem == newItem
        }
    }

    abstract inner class RecipeViewHolder(itemView: View) : ViewHolder(itemView) {
        abstract fun bind(recipe: Recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (recipeViewHolderType) {
            RecipeViewHolderType.GENERAL -> GeneralRecipeViewHolder(
                RecipeItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            RecipeViewHolderType.IN_INGREDIENT_DETAIL -> IngredientRecipeViewHolder(
                IngredientRecipeItemBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class GeneralRecipeViewHolder(private val binding: RecipeItemBinding) :
        RecipeViewHolder(binding.root) {
        override fun bind(recipe: Recipe) {
            binding.apply {
                tvRecipeTitle.text = recipe.title
                btnFavourite.isChecked = recipe.favourite
                chipPrice.text =
                    createPriceString(recipe.price, root.context.getString(R.string.price_format))
                chipType.applyRecipe(recipe)
                btnDelete.setOnClickListener {
                    actions?.onDelete(recipe)
                }
                btnFavourite.setOnClickListener {
                    actions?.onFavourite(recipe)
                }
            }
            itemView.setOnClickListener {
                actions?.onClick(recipe)
            }
        }
    }

    inner class IngredientRecipeViewHolder(private val binding: IngredientRecipeItemBinding) :
        RecipeViewHolder(binding.root) {
        override fun bind(recipe: Recipe) {
            binding.apply {
                tvRecipeTitle.text = recipe.title
            }
        }
    }
}