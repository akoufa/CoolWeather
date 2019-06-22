package com.akoufatzis.coolweather.data.places

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.akoufatzis.coolweather.data.places.core.await
import com.akoufatzis.coolweather.domain.Failure
import com.akoufatzis.coolweather.domain.Result
import com.akoufatzis.coolweather.domain.Success
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

private const val TAG = "LocationProvider"

class LocationProvider @Inject constructor(context: Context) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    private val geocoder: Geocoder = Geocoder(context, Locale.getDefault())

    @SuppressLint("MissingPermission")
    suspend fun getLocation(): Result<Location> {
        val lastLocation = fusedLocationClient.lastLocation.await() ?: return Failure(Exception("PlaceEntity is null"))
        return Success(lastLocation)
    }

    @SuppressLint("MissingPermission")
    suspend fun getLocalityName(): String? = withContext(Dispatchers.IO) {
        val location = fusedLocationClient.lastLocation.await() ?: return@withContext null
        try {
            val addresses = geocoder.getFromLocation(
                location.latitude,
                location.longitude,
                // In this sample, we get just a single address.
                1
            )
            if (addresses != null) {
                val firstAddress = addresses.firstOrNull()
                if (firstAddress != null) {
                    return@withContext firstAddress.locality
                }
            }
        } catch (ioException: IOException) {
            // Catch network or other I/O problems.
            Log.e(TAG, "Geocoding error", ioException)
        } catch (illegalArgumentException: IllegalArgumentException) {
            // Catch invalid latitude or longitude values.
            Log.e(
                TAG, "Latitude = $location.latitude , " +
                        "Longitude =  $location.longitude", illegalArgumentException
            )
        }

        null
    }
}
