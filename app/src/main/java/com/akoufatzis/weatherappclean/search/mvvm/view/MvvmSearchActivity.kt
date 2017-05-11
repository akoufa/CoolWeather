package com.akoufatzis.weatherappclean.search.mvvm.view

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.akoufatzis.weatherappclean.MainApplication
import com.akoufatzis.weatherappclean.R
import com.akoufatzis.weatherappclean.config.LOG_TAG
import com.akoufatzis.weatherappclean.databinding.ActivitySearchBinding
import com.akoufatzis.weatherappclean.di.components.DaggerSearchActivityComponent
import com.akoufatzis.weatherappclean.di.components.SearchActivityComponent
import com.akoufatzis.weatherappclean.search.mvp.view.CityWeatherAdapter
import com.akoufatzis.weatherappclean.search.mvvm.viewmodel.SearchViewModel
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MvvmSearchActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: SearchViewModel

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
        setupUI()
        bind()
    }

    private fun bind() {
        val searchDisposable = viewModel
                .search(RxTextView.textChanges(binding.etSearch))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.addCityWeather(it)
                }, {
                    Log.e(LOG_TAG, it.toString())
                })

        compositeDisposable.add(searchDisposable)

        val loadingDisposable = viewModel
                .loading()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {
                    Log.e(LOG_TAG, it.toString())
                })

        compositeDisposable.add(loadingDisposable)
    }

    fun unbind() {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    private fun setupUI() {
        binding.rvSearchResults.layoutManager = LinearLayoutManager(this)
        binding.rvSearchResults.adapter = adapter
        binding.rvSearchResults.setHasFixedSize(true)
    }

    override fun onDestroy() {
        unbind()
        super.onDestroy()
    }
}
