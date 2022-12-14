package com.rgdev.android.cookingbuddy.ui.fragments.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgdev.android.cookingbuddy.databinding.FragmentIngredientsBinding
import com.rgdev.android.cookingbuddy.ui.viewmodels.ingredient.IngredientsViewModel
import com.rgdev.android.cookingbuddy.ui.widget.ingredientlist.IngredientActions
import com.rgdev.android.cookingbuddy.ui.widget.ingredientlist.IngredientAdapter
import com.rgdev.android.cookingbuddy.ui.widget.utils.IngredientViewHolderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private val viewModel by viewModels<IngredientsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            model = viewModel

            rvIngredients.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter =
                    IngredientAdapter(IngredientViewHolderType.REGULAR, object : IngredientActions {
                        override fun onClick(ingredientId: Long) {
                            findNavController().navigate(
                                IngredientsFragmentDirections.actionIngredientsFragmentToIngredientDetailFragment(
                                    ingredientId
                                )
                            )
                        }
                    })
            }

            btnAddIngredient.setOnClickListener {
                findNavController().navigate(IngredientsFragmentDirections.actionIngredientsFragmentToAddIngredientFragment())
            }
        }
    }
}