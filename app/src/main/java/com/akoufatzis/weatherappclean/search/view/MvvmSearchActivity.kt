package com.akoufatzis.weatherappclean.search.view

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.akoufatzis.weatherappclean.R
import com.akoufatzis.weatherappclean.config.LOG_TAG
import com.akoufatzis.weatherappclean.databinding.ActivitySearchBinding
import com.akoufatzis.weatherappclean.domain.models.Failure
import com.akoufatzis.weatherappclean.domain.models.Result
import com.akoufatzis.weatherappclean.domain.models.Success
import com.akoufatzis.weatherappclean.search.CityWeatherAdapter
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel
import com.akoufatzis.weatherappclean.search.viewmodel.SearchViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MvvmSearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: SearchViewModel

    private lateinit var binding: ActivitySearchBinding

    private val compositeDisposable = CompositeDisposable()
    private val adapter = CityWeatherAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)
        setupUI()
        bind()
    }

    private fun bind() {
        val searchDisposable = viewModel
                .search(RxTextView.textChanges(binding.etSearch))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onStateChanged(it)
                }, {
                    Log.e(LOG_TAG, it.toString())
                })

        compositeDisposable.add(searchDisposable)
    }

    private fun onStateChanged(result: Result<CityWeatherModel>){
        binding.srContainer.isRefreshing = result.loading
        when(result){
            is Success -> {
                binding.srContainer.isRefreshing = result.loading
                adapter.addCityWeather(result.data!!)
                binding.rvSearchResults.scrollToPosition(0)
            }
            is Failure -> Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun setupUI() {
        binding.apply {
            rvSearchResults.layoutManager = LinearLayoutManager(this@MvvmSearchActivity)
            rvSearchResults.adapter = adapter
            rvSearchResults.setHasFixedSize(true)
        }
    }

    override fun onDestroy() {
        unbind()
        super.onDestroy()
    }
}
