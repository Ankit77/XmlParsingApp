package com.example.ankit.xmlparsingapp.googlemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ankit.xmlparsingapp.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by ANKIT on 9/23/2016.
 */

public class MapDisplayActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener {
    MapView mapView;
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdisplay);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        // Gets to GoogleMap from the MapView and does initialization stuff
//        map = mapView.getMap();
//        map.getUiSettings().setMyLocationButtonEnabled(false);
//        map.setMyLocationEnabled(true);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions()
                .position(new LatLng(28.704059, 77.102490)).draggable(true)
                .title("Marker").snippet("this is delhi").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher))).showInfoWindow();

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(28.704059, 77.102490), 10);
        map.animateCamera(cameraUpdate);
        map.setOnMarkerClickListener(this);
        map.setOnMarkerDragListener(this);
        map.setOnInfoWindowClickListener(this);
        map.setOnMapClickListener(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Toast.makeText(this,
                marker.getTitle() +
                        " has been clicked " + marker.getPosition().latitude + " times.",
                Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        Toast.makeText(this,
                marker.getTitle() +
                        " has been clicked " + marker.getPosition().latitude + " times.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        Toast.makeText(this,
                marker.getTitle() +
                        " has been clicked " + marker.getPosition().latitude + " times.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this,
                marker.getTitle() +
                        " has been clicked " + marker.getPosition().latitude + " times.",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapClick(LatLng latLng) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latLng.latitude, latLng.longitude)).draggable(true)
                .title("Marker").snippet("this is delhi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))).showInfoWindow();
    }
}
