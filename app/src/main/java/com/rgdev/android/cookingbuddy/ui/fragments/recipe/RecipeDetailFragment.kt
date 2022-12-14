package com.rgdev.android.cookingbuddy.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgdev.android.cookingbuddy.databinding.FragmentRecipeDetailBinding
import com.rgdev.android.cookingbuddy.ui.viewmodels.recipe.RecipeDetailViewModel
import com.rgdev.android.cookingbuddy.ui.widget.ingredientlist.IngredientAdapter
import com.rgdev.android.cookingbuddy.ui.widget.utils.IngredientViewHolderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailFragment : Fragment() {

    private lateinit var binding: FragmentRecipeDetailBinding
    private val viewModel by viewModels<RecipeDetailViewModel>()
    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel

            rvIngredients.layoutManager = LinearLayoutManager(context)
            rvIngredients.adapter = IngredientAdapter(IngredientViewHolderType.REGULAR,null)

            btnFavourite.setOnClickListener {
                viewModel.addFavouriteRecipe(viewModel.recipe.value?.recipe)
            }
            btnDelete.setOnClickListener {
                viewModel.deleteRecipe(viewModel.recipe.value?.recipe)
                findNavController().navigate(RecipeDetailFragmentDirections.actionRecipeDetailFragmentToFragmentRecipes())
            }
        }
        viewModel.loadRecipe(args.id)
    }
}