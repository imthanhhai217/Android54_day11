package com.devpro.android54_day11.utils

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionManager {
    val locationPermission = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
    val extendStoragePermission = arrayOf(Manifest.permission.READ_MEDIA_IMAGES)

    fun requestLocationPermission(activity:Activity, resultLauncher:ActivityResultLauncher<Array<String>>){
        if (!checkSelfPermission(activity, locationPermission)){
            resultLauncher.launch(locationPermission)
        }
    }

    private fun checkSelfPermission(activity:Activity, permissions:Array<String>):Boolean{
        for (per in permissions){
            if (ContextCompat.checkSelfPermission(activity,per) == PackageManager.PERMISSION_GRANTED){
                return true
            }
        }
        return false
    }
}