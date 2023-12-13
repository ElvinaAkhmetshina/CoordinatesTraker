package ru.el.coordinatestracker.locating

import android.location.Location
import android.location.LocationRequest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
/*
object Locator {

    val locationRequest by lazy {
        LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            10000
        ).build()
    }

    private val _location: MutableStateFlow<Location?> = MutableStateFlow(null)
    val location: StateFlow<Location?>
        get() = _location

    var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (loc in locationResult.locations) {
                CoroutineScope(Dispatchers.IO).launch {
                    _location.emit(loc)
                }
            }
        }
    }
}*/