package com.akoufatzis.coolweather.presentation.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentMainBinding
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.Autocomplete
import com.google.android.libraries.places.widget.AutocompleteActivity
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode
import dagger.android.support.DaggerFragment
import javax.inject.Inject

const val AUTOCOMPLETE_REQUEST_CODE = 1
const val TAG = "MainFragment"

class MainFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PagerAdapter

    private val mainViewModel by viewModels<MainViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = FragmentMainBinding.inflate(inflater, container, false)
        adapter = PagerAdapter(childFragmentManager, lifecycle)
        binding.places.adapter = adapter

        val indicator = binding.indicator
        indicator.setViewPager(binding.places)
        adapter.registerAdapterDataObserver(indicator.adapterDataObserver)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.viewState.observe(viewLifecycleOwner) {
            val data = it.data
            if (data.isNotEmpty()) {
                adapter.setPlaces(data)
                binding.places.visibility = View.VISIBLE
                binding.emptyScreen.visibility = View.GONE
            }else{
                binding.places.visibility = View.GONE
                binding.emptyScreen.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.search_place -> {
                searchForPlace()
                true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val place = Autocomplete.getPlaceFromIntent(data!!)
                    val name = place.name
                    val id = place.id
                    if (name != null && id != null) {
                        mainViewModel.storePlace(com.akoufatzis.coolweather.domain.place.Place(name, id))
                    }
                }
                AutocompleteActivity.RESULT_ERROR -> {
                    // TODO: Handle the error.
                    val status = Autocomplete.getStatusFromIntent(data!!)
                    Log.d(TAG, status.statusMessage!!)
                }
                Activity.RESULT_CANCELED -> {
                    // The user canceled the operation.
                }
            }
        }
    }

    private fun searchForPlace() {
        val fields = listOf(Place.Field.NAME, Place.Field.ID)
        val intent =
            Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                .setTypeFilter(TypeFilter.CITIES)
                .build(context!!)
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE)
    }
}