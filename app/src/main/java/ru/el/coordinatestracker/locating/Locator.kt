package ru.el.coordinatestracker.locating

import android.location.Location
//import android.location.LocationRequest
import android.renderscript.RenderScript
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch




import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority


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
}