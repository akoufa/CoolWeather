@file:Suppress("ForbiddenComment")

package com.akoufatzis.coolweather.presentation.weather

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentWeatherBinding
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject


const val AUTOCOMPLETE_REQUEST_CODE = 1
const val TAG = "WeatherFragment"


class WeatherFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val weatherViewModel: WeatherViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(WeatherViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)

        // Inflate the layout for this fragment
        val binding =
            DataBindingUtil.inflate<FragmentWeatherBinding>(
                inflater,
                R.layout.fragment_weather,
                container,
                false
            )
        weatherViewModel.viewState.observe(viewLifecycleOwner, Observer { state ->

            if (state.error != null) {
                // TODO: Enable reload here
                Snackbar.make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_SHORT)
                    .show()
            } else if (state.data != null) {
                binding.ivWeather.setImageResource(state.data.iconRes)
                binding.tvCityName.text = state.data.city
                binding.tvDegrees.text = getDegreesRepresentation(context!!, state.data.tempData)
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.weather_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_place -> {
                searchForPlace()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    val name = place.name
                    if (name != null) {
                        weatherViewModel.showWeather(name)
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    val status = Autocomplete.getStatusFromIntent(data!!)
                    Log.d(TAG, status.statusMessage!!)
                }
                RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
        }
    }

    private fun searchForPlace() {
        val fields = listOf(Place.Field.NAME)
        val intent =
            Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .setTypeFilter(TypeFilter.CITIES)
                .build(context!!)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }
}
