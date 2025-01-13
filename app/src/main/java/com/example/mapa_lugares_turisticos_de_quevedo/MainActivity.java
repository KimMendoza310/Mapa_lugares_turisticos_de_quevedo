package com.example.mapa_lugares_turisticos_de_quevedo;

import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity
        extends AppCompatActivity
    implements OnMapReadyCallback
{

    GoogleMap mapa;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;
        mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mapa.getUiSettings().setZoomControlsEnabled(true);
        LatLng posQuevedo = new LatLng(-1.0225794371776167, -79.4600296519477);
        CameraUpdate camUpd1 =
                CameraUpdateFactory
                        .newLatLngZoom(posQuevedo, 15);
        mapa.moveCamera(camUpd1);
        /*CameraPosition camPos = new CameraPosition.Builder()
                .target(posQuevedo)
                .zoom(19)
                .bearing(45) //noreste arriba
                .tilt(70) //punto de vista de la cámara 70 grados
                .build();
        CameraUpdate camUpd3 =
                CameraUpdateFactory.newCameraPosition(camPos);
        mapa.animateCamera(camUpd3);*/
        PolylineOptions lineas = new
                PolylineOptions()
                .add(new LatLng(-0.96986420, -79.472134))
                .add(new LatLng(-0.9759875, -79.43293976))
                .add(new LatLng(-1.0678363459349791, -79.44967932579355))
                .add(new LatLng(-1.059468009763, -79.497448305))
                .add(new LatLng(-0.96986420, -79.472134));
        lineas.width(8);
        lineas.color(Color.YELLOW);
        mapa.addPolyline(lineas);

        mapa.addMarker(new MarkerOptions()
                .position(posQuevedo)
                .title("Centro De Quevedo"));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-0.96986420, -79.472134)));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-0.9759875, -79.43293976)));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.0678363459349791, -79.44967932579355)));
        mapa.addMarker(new MarkerOptions()
                .position(new LatLng(-1.059468009763, -79.497448305)));

        mapa.setOnMapClickListener(new  GoogleMap.OnMapClickListener() {
            public void onMapClick(LatLng point) {
               mapa.addMarker(new MarkerOptions()
                       .position(new LatLng(point.latitude, point.longitude)));
            }
        });

        Circle circulo;
        CircleOptions circleOptions = new CircleOptions()
                .center(new LatLng(-1.0225794371776167, -79.4600296519477))
                .radius(5000)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(50, 150, 50, 50));
        circulo = mapa.addCircle(circleOptions);



    }
}