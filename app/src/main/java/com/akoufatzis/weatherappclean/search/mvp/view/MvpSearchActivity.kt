package com.akoufatzis.weatherappclean.search.mvp.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.akoufatzis.weatherappclean.MainApplication
import com.akoufatzis.weatherappclean.R
import com.akoufatzis.weatherappclean.config.LOG_TAG
import com.akoufatzis.weatherappclean.databinding.ActivitySearchBinding
import com.akoufatzis.weatherappclean.di.components.DaggerSearchActivityComponent
import com.akoufatzis.weatherappclean.di.components.SearchActivityComponent
import com.akoufatzis.weatherappclean.search.model.CityWeatherModel
import com.akoufatzis.weatherappclean.search.mvp.SearchContract
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MvpSearchActivity : AppCompatActivity(), SearchContract.View {

    // Just for demo purposes to show how orientation change is handled
    // It does not make a lot sense to store dynamic data like search results
    val lastSearchExtraKey = "lastSearchExtraKey"
    var lastSearchTerm: String? = null

    @Inject
    lateinit var presenter: SearchContract.Presenter

    val searchComponent: SearchActivityComponent by lazy {
        DaggerSearchActivityComponent
                .builder()
                .appComponent((application as MainApplication).appComponent)
                .build()
    }

    lateinit var binding: ActivitySearchBinding

    val compositeDisposable = CompositeDisposable()
    val adapter = CityWeatherAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivitySearchBinding>(this, R.layout.activity_search)
        searchComponent.inject(this)
        presenter.attachView(this)
        if (savedInstanceState != null) {
            lastSearchTerm = savedInstanceState[lastSearchExtraKey] as? String
        }

        setupUI()
        bind()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (lastSearchTerm != null) {
            outState.putString(lastSearchExtraKey, lastSearchTerm)
        }
    }

    private fun setupUI() {
        binding.rvSearchResults.layoutManager = LinearLayoutManager(this)
        binding.rvSearchResults.adapter = adapter
        binding.rvSearchResults.setHasFixedSize(true)
    }

    private fun bind() {

        presenter
                .search(RxTextView.textChanges(binding.etSearch))
    }

    override fun onDestroy() {
        presenter.detachView(false)
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
        super.onDestroy()
    }

    override fun showCityWeather(cityWeatherModel: CityWeatherModel) {
        adapter.addCityWeather(cityWeatherModel)
    }

    override fun showLoading(show: Boolean) {
        Log.d(LOG_TAG, "loading")
    }

    override fun showError() {
        Snackbar.make(binding.root, getString(R.string.error_occurred), Snackbar.LENGTH_SHORT)
                .show()
    }
}
