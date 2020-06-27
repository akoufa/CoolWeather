@file:Suppress("ForbiddenComment")

package com.akoufatzis.coolweather.presentation.weather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentWeatherBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

const val PLACE_NAME_KEY = "PLACE_NAME_KEY"

@AndroidEntryPoint
class WeatherFragment : Fragment() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    lateinit var binding: FragmentWeatherBinding

    private lateinit var placeName: String

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString(PLACE_NAME_KEY)?.let {
            placeName = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        weatherViewModel.viewState.observe(viewLifecycleOwner, Observer { state ->

            if (state.error != null) {
                // TODO: Enable reload here
                Snackbar.make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_SHORT)
                    .show()
            } else if (state.data != null) {
                binding.ivWeather.setImageResource(state.data.iconRes)
                binding.tvCityName.text = state.data.place
                binding.tvDegrees.text = getDegreesRepresentation(context!!, state.data.tempData)
            }
        })
    }

    override fun onStart() {
        super.onStart()
        weatherViewModel.showWeather(placeName)
    }

    companion object {
        // TODO: Use FragmentFactory and constructor injection instead
        @JvmStatic
        fun newInstance(placeName: String) = WeatherFragment().apply {
            arguments = Bundle().apply {
                putString(PLACE_NAME_KEY, placeName)
            }
        }
    }
}
