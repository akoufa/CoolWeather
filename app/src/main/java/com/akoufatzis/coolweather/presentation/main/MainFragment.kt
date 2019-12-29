package com.akoufatzis.coolweather.presentation.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentMainBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

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
            } else {
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
                findNavController().navigate(R.id.placesFragment)
                true
            }
            R.id.settings -> {
                findNavController().navigate(R.id.settingsFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
