package com.rgdev.android.cookingbuddy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.rgdev.android.cookingbuddy.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationDrawer()
    }

    override fun onSupportNavigateUp(): Boolean {
        var navController : NavController? = null
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag)
        navController = navHostFragment?.findNavController()
        return navController?.navigateUp(appBarConfiguration) == true || super.onSupportNavigateUp()
    }

    private fun setupNavigationDrawer(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view_tag)
        if (navHostFragment != null) {
            navController = navHostFragment.findNavController()
        }
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentRecipes, R.id.ingredientsFragment, R.id.aboutFragment),
            binding.drawerLayout
        )

        binding.navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

}