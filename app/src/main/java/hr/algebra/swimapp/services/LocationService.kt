package hr.algebra.swimapp.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import hr.algebra.swimapp.R
import hr.algebra.swimapp.fusedLocationClient
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

private const val LOCATION_REQUEST_CODE = 124
var lat = 0.0
var long = 0.0

@SuppressLint("MissingPermission")
@AfterPermissionGranted(LOCATION_REQUEST_CODE)
fun Activity.getCurrentLocation() {
    if (EasyPermissions.hasPermissions(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                lat = location.latitude
                long = location.longitude
            }
        }
    } else {
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.permission_rationale),
            LOCATION_REQUEST_CODE,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }
}