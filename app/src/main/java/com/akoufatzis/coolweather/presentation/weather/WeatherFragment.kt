package com.akoufatzis.coolweather.presentation.weather

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentWeatherBinding
import com.akoufatzis.coolweather.presentation.core.onEnterAction
import com.akoufatzis.coolweather.presentation.core.onRightDrawableClicked
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class WeatherFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val weatherViewModel: WeatherViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
    }

    private val weatherAdapter = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding =
            DataBindingUtil.inflate<FragmentWeatherBinding>(inflater, R.layout.fragment_weather, container, false)
        binding.rvWeather.adapter = weatherAdapter
        binding.rvWeather.layoutManager = LinearLayoutManager(context)
        binding.rvWeather.setHasFixedSize(true)

        binding.etCity.onRightDrawableClicked {
            Toast.makeText(context, R.string.not_implement_yet, Toast.LENGTH_SHORT).show()
        }

        binding.etCity.onEnterAction {
            weatherViewModel.showWeather(it.text.toString())
        }

        weatherViewModel.viewState.observe(viewLifecycleOwner, Observer { state ->

            if (state.error != null) {
                hideKeyboard()
                Snackbar.make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_SHORT)
                    .show()
            } else {
                val weatherList = state.data.map {
                    WeatherItem(
                        it.city,
                        getDegreesRepresentation(context!!, it.tempData),
                        it.iconRes
                    )
                }

                hideKeyboard()
                weatherAdapter.submitList(weatherList)
                binding.etCity.text?.clear()
            }
        })

        return binding.root
    }

    private fun hideKeyboard() {
        val view = activity?.window?.currentFocus
        view?.let { v ->
            val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(v.windowToken, 0)
        }
    }
}
