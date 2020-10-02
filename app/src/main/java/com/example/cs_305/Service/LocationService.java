package com.example.cs_305.Service;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

import com.example.cs_305.Tool.Location;

public class LocationService {
    public static Location getPosition(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            android.location.Location l = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (l != null) {
                return new Location(l.getLatitude(), l.getLongitude());
            }
        }
        return null;
    }
}
