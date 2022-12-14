package com.rgdev.android.cookingbuddy.ui.fragments.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgdev.android.cookingbuddy.databinding.FragmentIngredientDetailBinding
import com.rgdev.android.cookingbuddy.ui.viewmodels.ingredient.IngredientDetailViewModel
import com.rgdev.android.cookingbuddy.ui.widget.recipelist.RecipesAdapter
import com.rgdev.android.cookingbuddy.ui.widget.utils.RecipeViewHolderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientDetailFragment : Fragment() {

    private lateinit var binding: FragmentIngredientDetailBinding
    private val viewModel by viewModels<IngredientDetailViewModel>()
    private val args: IngredientDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel

            rvRecipes.layoutManager = LinearLayoutManager(context)
            rvRecipes.adapter = RecipesAdapter(RecipeViewHolderType.IN_INGREDIENT_DETAIL, null)
        }
        viewModel.loadIngredient(args.ingredientId)
    }
}