package com.akoufatzis.coolweather.presentation.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.akoufatzis.coolweather.databinding.FragmentSettingsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val settingsViewModel: SettingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)

        settingsViewModel.viewState.observe(viewLifecycleOwner, Observer {
            if (it.data != null) {
                when (it.data) {
                    TemperatureUnit.CELSIUS -> {
                        binding.tbTempUnit.isChecked = true
                    }
                    TemperatureUnit.FAHRENHEIT -> {
                        binding.tbTempUnit.isChecked = false
                    }
                }
            }
        })

        binding.tbTempUnit.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                settingsViewModel.setTemperatureUnit(TemperatureUnit.CELSIUS)
            } else {
                settingsViewModel.setTemperatureUnit(TemperatureUnit.FAHRENHEIT)
            }
        }
        return binding.root
    }
}
