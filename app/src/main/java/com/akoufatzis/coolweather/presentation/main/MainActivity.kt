package com.akoufatzis.coolweather.presentation.main

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val appBarConfiguration: AppBarConfiguration by lazy(LazyThreadSafetyMode.NONE) {
        AppBarConfiguration(
            setOf(
                R.id.weatherFragment,
                R.id.settingsFragment
            )
        )
    }

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        )
        setupActionBar(navController, binding.toolbar)
        NavigationUI.setupWithNavController(binding.toolbar, navController)
    }

    override fun onSupportNavigateUp() =
        NavigationUI.navigateUp(navController, appBarConfiguration) ||
                navController.navigateUp()

    private fun setupActionBar(navController: NavController, toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration)
    }
}
