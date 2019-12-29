package com.akoufatzis.coolweather.presentation.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentPlacesBinding
import com.akoufatzis.coolweather.presentation.core.hideKeyboard
import com.akoufatzis.coolweather.presentation.core.onTextChange
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PlacesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentPlacesBinding
    private lateinit var placesAdapter: PlacesAdapter

    private val placesViewModel: PlacesViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, viewModelFactory).get(PlacesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlacesBinding.inflate(inflater, container, false)
        placesAdapter = PlacesAdapter()
        binding.rvPlaces.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = placesAdapter
        }

        placesAdapter.onClick = {
            placesViewModel.storePlace(it)
            context?.hideKeyboard(view!!)
            findNavController().navigate(R.id.mainFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        placesViewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            if (state.error != null) {
                // TODO: Enable reload here
                Snackbar.make(binding.root, R.string.something_went_wrong, Snackbar.LENGTH_SHORT)
                    .show()
            } else if (state.data != null) {
                placesAdapter.submitList(state.data)
            }
        })
        placesViewModel.searchPlaces(binding.tiSearchText.onTextChange())
    }
}
