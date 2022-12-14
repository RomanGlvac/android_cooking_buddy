package com.rgdev.android.cookingbuddy.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgdev.android.cookingbuddy.R
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.databinding.FragmentRecipesBinding
import com.rgdev.android.cookingbuddy.ui.utils.RecipeSortType
import com.rgdev.android.cookingbuddy.ui.viewmodels.recipe.RecipesViewModel
import com.rgdev.android.cookingbuddy.ui.widget.recipelist.RecipeActions
import com.rgdev.android.cookingbuddy.ui.widget.recipelist.RecipesAdapter
import com.rgdev.android.cookingbuddy.ui.widget.utils.RecipeViewHolderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var binding: FragmentRecipesBinding
    private val viewModel by viewModels<RecipesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            lifecycleOwner = viewLifecycleOwner
            model = viewModel

            rvRecipes.layoutManager = LinearLayoutManager(root.context)
            rvRecipes.adapter =
                RecipesAdapter(RecipeViewHolderType.GENERAL, object : RecipeActions {
                    override fun onClick(recipe: Recipe) {
                        findNavController().navigate(
                            RecipesFragmentDirections.actionFragmentRecipesToRecipeDetailFragment(
                                recipe.recipeId
                            )
                        )
                    }

                    override fun onFavourite(recipe: Recipe) {
                        recipe.favourite = !recipe.favourite
                        viewModel.updateRecipe(recipe)
                    }

                    override fun onDelete(recipe: Recipe) {
                        viewModel.deleteRecipe(recipe)
                    }
                })
            setUpSpinner()


            btnAddRecipe.setOnClickListener {
                findNavController().navigate(
                    RecipesFragmentDirections.actionFragmentRecipesToAddRecipeFragment()
                )
            }
        }
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.sorting_options,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnSort.adapter = it
            binding.spnSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (position) {
                        0 -> viewModel.setSortType(RecipeSortType.NAME)
                        1 -> viewModel.setSortType(RecipeSortType.PRICE)
                        2 -> viewModel.setSortType(RecipeSortType.FAVOURITE)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }
    }

}