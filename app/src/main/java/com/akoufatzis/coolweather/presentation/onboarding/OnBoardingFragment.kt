package com.akoufatzis.coolweather.presentation.onboarding

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.akoufatzis.coolweather.R
import com.akoufatzis.coolweather.databinding.FragmentOnBoardingBinding
import com.akoufatzis.coolweather.presentation.navigation.Actions
import dagger.android.support.DaggerFragment
import javax.inject.Inject

private const val REQUEST_ACCESS_COARSE_LOCATION = 1000
private const val TAG = "OnBoardingFragment"

class OnBoardingFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: OnBoardingViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, viewModelFactory).get(OnBoardingViewModel::class.java)
    }

    private lateinit var bLocation: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =
            DataBindingUtil.inflate<FragmentOnBoardingBinding>(
                inflater,
                R.layout.fragment_on_boarding,
                container,
                false
            )

        binding.bLocation.setOnClickListener {
            requestPermission()
        }

        bLocation = binding.bLocation
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            activity!!.startActivity(Actions.openMainIntent(context!!))
        })

        return binding.root
    }

    private fun requestPermission() {
        if (ContextCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity!!,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                Log.d(TAG, "Should display permission rationale")
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    activity!!,
                    arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    REQUEST_ACCESS_COARSE_LOCATION
                )
            }
        } else {
            // Permission has already been granted
            fetchPlace()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_ACCESS_COARSE_LOCATION -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // permission was granted, yay!
                    fetchPlace()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun fetchPlace() {
        bLocation.visibility = View.GONE
        viewModel.fetchPlace()
    }
}
