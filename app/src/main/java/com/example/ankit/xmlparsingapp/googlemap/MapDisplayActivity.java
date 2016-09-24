package com.example.ankit.xmlparsingapp.googlemap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ANKIT on 9/23/2016.
 */

public class MapDisplayActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapClickListener, RoutingListener {
    MapView mapView;
    GoogleMap map;
    private LatLng start;
    private LatLng end;
    private List<Polyline> polylines;
    private static final int[] COLORS = new int[]{R.color.primary_dark, R.color.primary, R.color.primary_light, R.color.accent, R.color.primary_dark_material_light};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapdisplay);
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        polylines = new ArrayList<>();
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

        start = new LatLng(28.704059, 77.102490);
        end = latLng;

        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.DRIVING)
                .withListener(this)
                .waypoints(start, end).alternativeRoutes(true)
                .build();
        routing.execute();
        map.addMarker(new MarkerOptions()
                .position(new LatLng(latLng.latitude, latLng.longitude)).draggable(true)
                .title("Marker").snippet("this is delhi").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))).showInfoWindow();
    }


    @Override
    public void onRoutingFailure(RouteException e) {

    }

    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int i) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(start);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);

        map.moveCamera(center);


        if (polylines.size() > 0) {
            for (Polyline poly : polylines) {
                poly.remove();
            }
        }

        polylines = new ArrayList<>();
        //add route(s) to the map.
        for (int k = 0; k < route.size(); k++) {

            //In case of more than 5 alternative routes
            int colorIndex = i % COLORS.length;

            PolylineOptions polyOptions = new PolylineOptions();
            polyOptions.color(getResources().getColor(COLORS[colorIndex]));
            polyOptions.width(10 + i * 3);
            polyOptions.addAll(route.get(i).getPoints());
            Polyline polyline = map.addPolyline(polyOptions);
            polylines.add(polyline);

            Toast.makeText(getApplicationContext(), "Route " + (i + 1) + ": distance - " + route.get(i).getDistanceValue() + ": duration - " + route.get(i).getDurationValue(), Toast.LENGTH_SHORT).show();
        }

        // Start marker
        MarkerOptions options = new MarkerOptions();
        options.position(start);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
        map.addMarker(options);

        // End marker
        options = new MarkerOptions();
        options.position(end);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher));
        map.addMarker(options);

    }

    @Override
    public void onRoutingCancelled() {

    }
}
