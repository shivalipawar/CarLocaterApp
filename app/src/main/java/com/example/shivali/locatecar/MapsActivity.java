package com.example.shivali.locatecar;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static MapsActivity instance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        instance = this;

    }
    public static MapsActivity getInstance(){
        return instance;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        final double[] lat = {16.846400};
        final double[] lon = {74.601400};
         final MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(lat[0], lon[0])).title("WCE Sangli").icon(BitmapDescriptorFactory.fromResource(R.drawable.car1));
         final Marker marker = mMap.addMarker(markerOptions);
        }

    public void updateVehicleLocation(double latitude, double longitude, String carOwner) {
        Geocoder gcd = new Geocoder(getApplicationContext(), Locale.getDefault());
        List<Address> addresses;
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        String cityName=carOwner+" 's vechicle Zoom in to see location";
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            cityName = carOwner + "'s Vechicle "+ addresses.get(0).getAddressLine(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(latitude, longitude)).title(cityName).icon(BitmapDescriptorFactory.fromResource(R.drawable.car1));
        mMap.addMarker(markerOptions);
     }
}