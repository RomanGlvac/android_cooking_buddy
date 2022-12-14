package com.rgdev.android.cookingbuddy.ui.fragments.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rgdev.android.cookingbuddy.R
import com.rgdev.android.cookingbuddy.data.models.Recipe
import com.rgdev.android.cookingbuddy.data.models.RecipeType
import com.rgdev.android.cookingbuddy.databinding.FragmentAddRecipeBinding
import com.rgdev.android.cookingbuddy.ui.viewmodels.recipe.AddRecipeViewModel
import com.rgdev.android.cookingbuddy.ui.widget.ingredientlist.IngredientAdapter
import com.rgdev.android.cookingbuddy.ui.widget.utils.IngredientViewHolderType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddRecipeFragment : Fragment() {

    private lateinit var binding: FragmentAddRecipeBinding
    private val viewModel by viewModels<AddRecipeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            lifecycleOwner = viewLifecycleOwner
            model = viewModel

            rvIngredients.layoutManager = LinearLayoutManager(context)
            rvIngredients.adapter = IngredientAdapter(
                IngredientViewHolderType.ADD_RECIPE,
                null
            )

            btnSave.setOnClickListener {
                if (checkTextInputs()){
                    viewModel.addRecipe(
                        Recipe(
                            title = tvRecipeTitle.editText?.text.toString(),
                            recipeText = tvRecipeText.editText?.text.toString(),
                            price = viewModel.addedIngredients.value?.fold(0.0){acc, ingredient -> acc + ingredient.price }!!,
                            type = RecipeType.fromString(spnCategory.selectedItem.toString()),
                            favourite = false
                        )
                    )
                    if(chkCloseOnAdd.isChecked) {
                        viewModel.recipeAdded.observe(viewLifecycleOwner){
                            if(it){
                                findNavController().popBackStack()
                            }
                        }
                    } else {
                        tvRecipeText.editText?.text?.clear()
                        tvRecipeTitle.editText?.text?.clear()
                    }
                }
            }
        }
        setUpSpinner()
    }

    private fun setUpSpinner() {
        ArrayAdapter.createFromResource(requireContext(), R.array.categories_array, android.R.layout.simple_spinner_item).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spnCategory.adapter = it
        }
    }

    private fun checkTextInputs(): Boolean {
        val titleEntered = binding.tvRecipeTitle.editText?.text.toString().isNotBlank()
        val textEntered = binding.tvRecipeText.editText?.text.toString().isNotBlank()
        return titleEntered && textEntered
    }
}