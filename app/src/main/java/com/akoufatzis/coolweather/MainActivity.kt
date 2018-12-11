package com.akoufatzis.coolweather

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.databinding.DataBindingUtil
import com.akoufatzis.coolweather.databinding.ActivityMainBindingImpl
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setupActionBar(navController)
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp()

    private fun setupActionBar(navController: NavController) {
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private val binding: ActivityMainBindingImpl by lazy {
        DataBindingUtil.setContentView<ActivityMainBindingImpl>(this,
            R.layout.activity_main
        )
    }
}
