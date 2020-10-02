package com.example.cs_305.Service;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.example.cs_305.Tool.Location;

import java.io.IOException;
import java.util.List;

public class GeocodingService {

    private final Geocoder geocoder;
    public GeocodingService(Context context) {
        geocoder = new Geocoder(context);
    }

    public String getAddress(Location location) {
        if(location != null){
            try {
                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                if(addresses.size() > 0) {
                    Address address = addresses.get(0);
                    if(address != null) {
                        String res = null;
                        String tmp = null;
                        int i=0;
                        do {
                            tmp = address.getAddressLine(i++);
                            if(tmp != null){
                                res = res == null ? tmp : res + ", " + tmp;
                            }
                        } while (tmp != null);
                        return res;
                    }
                }
            } catch (IOException e) { }
        }
        return null;
    }

    public Location getlocation(String Address) {
        try {
            List<Address> addresses = geocoder.getFromLocationName(Address, 1);
            if(addresses.size() > 0) {
                Address address = addresses.get(0);
                if(address != null) {
                    return new Location(address.getLatitude(), address.getLongitude());
                }
            }
        } catch (IOException e) { }
        return null;
    }
}
