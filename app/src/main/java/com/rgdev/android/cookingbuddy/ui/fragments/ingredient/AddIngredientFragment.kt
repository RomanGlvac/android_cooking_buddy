package com.rgdev.android.cookingbuddy.ui.fragments.ingredient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.rgdev.android.cookingbuddy.data.models.Ingredient
import com.rgdev.android.cookingbuddy.databinding.FragmentAddIngredientBinding
import com.rgdev.android.cookingbuddy.ui.viewmodels.ingredient.AddIngredientViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddIngredientFragment : Fragment() {

    private lateinit var binding: FragmentAddIngredientBinding
    private val viewModel by viewModels<AddIngredientViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddIngredientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnAddIngredient.setOnClickListener {
                viewModel.addIngredient(
                    Ingredient(
                        name = etIngredientTitle.editText?.text.toString(),
                        price = etIngredientPrice.editText?.text.toString().toDouble()
                    )
                )
                if(chkCloseOnAdd.isChecked){
                    findNavController().popBackStack()
                }
            }
        }
    }

    fun checkInputs(): Boolean {
        val titleEntered = binding.etIngredientTitle.editText?.text.toString().isNotBlank()
        val priceEntered = binding.etIngredientPrice.editText?.text.toString().isNotBlank()
        return titleEntered && priceEntered
    }
}