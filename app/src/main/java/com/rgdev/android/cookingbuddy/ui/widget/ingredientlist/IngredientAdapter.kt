package com.rgdev.android.cookingbuddy.ui.widget.ingredientlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.databinding.IngredientItemBinding
import com.rgdev.android.cookingbuddy.databinding.RecipeIngredientItemBinding
import com.rgdev.android.cookingbuddy.ui.widget.utils.IngredientViewHolderType

class IngredientAdapter(
    private val ingredientViewHolderType: IngredientViewHolderType,
    private val actions: IngredientActions?
) :
    ListAdapter<Ingredient, IngredientAdapter.IngredientViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.ingredientId == newItem.ingredientId
        }


        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem == newItem
        }
    }

    abstract inner class IngredientViewHolder(itemView: View) : ViewHolder(itemView) {
        abstract fun bind(ingredient: Ingredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (ingredientViewHolderType) {
            IngredientViewHolderType.REGULAR -> GeneralIngredientViewHolder(
                IngredientItemBinding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            )
            IngredientViewHolderType.ADD_RECIPE -> RecipeIngredientViewHolder(
                RecipeIngredientItemBinding.inflate(layoutInflater, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class GeneralIngredientViewHolder(private val binding: IngredientItemBinding) :
        IngredientViewHolder(binding.root) {
        override fun bind(ingredient: Ingredient) {
            binding.apply {
                tvIngredientTitle.text = ingredient.name
                tvIngredientPrice.text = ingredient.price.toString()
            }
            itemView.setOnClickListener {
                actions?.onClick(ingredient.ingredientId)
            }
        }
    }

    inner class RecipeIngredientViewHolder(private val binding: RecipeIngredientItemBinding) :
        IngredientViewHolder(binding.root) {
        override fun bind(ingredient: Ingredient) {
            binding.apply {
                tvIngredientTitle.text = ingredient.name
                chkIncludeIngredient.setOnCheckedChangeListener { _, _ ->
                    actions?.onIngredientAdd(ingredient)
                }
            }
        }
    }

}