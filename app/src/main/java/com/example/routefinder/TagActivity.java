package com.example.routefinder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;

import androidx.annotation.NonNull;
import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;


public class TagActivity extends AppCompatActivity {

    private MapView mapView;
    private MarkerViewManager markerViewManager;
    ArrayList<LatLng> latLngs = new ArrayList<LatLng>();
    ArrayList<String> locationsInfo = new ArrayList<String>();
    ArrayList<MarkerView> markerViews = new ArrayList<MarkerView>();
    private int numberOfData;

    public void manageData()
    {
        numberOfData = 2;

        latLngs.add(new LatLng(-7.292992,112.8072211));
        latLngs.add(new LatLng(-7.292751,112.807925));

        locationsInfo.add("Location Info 1");
        locationsInfo.add("Location Info 2");

        for(int i=0;i<numberOfData;i++) {
            View view;
            view = LayoutInflater.from(TagActivity.this).inflate(R.layout.marker_view, null);
            view.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

            TextView locationInfoTextView;
            locationInfoTextView = view.findViewById(R.id.marker_location_info);
            locationInfoTextView.setText(locationsInfo.get(i));

            MarkerView markerView;
            markerView = new MarkerView(latLngs.get(i),view);
            markerViews.add(markerView);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getApplicationContext(), getString(R.string.mapbox_access_token));
        setContentView(R.layout.activity_tag);
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull final MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                    @Override
                    public void onStyleLoaded(@NonNull Style style) {
                        markerViewManager = new MarkerViewManager(mapView,mapboxMap);
                        manageData();
                        for(int i=0;i<markerViews.size();i++) {
                            markerViewManager.addMarker(markerViews.get(i));
                        }
                    }
                });
            }
        });
    }

    // Add the mapView lifecycle to the activity's lifecycle methods
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        markerViewManager.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}