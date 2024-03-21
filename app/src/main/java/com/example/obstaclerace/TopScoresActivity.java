package com.example.obstaclerace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TopScoresActivity extends AppCompatActivity{

    private TopScoresFragment fragmentScores;
    private MapsFragment fragmentMaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);
        fragmentScores = new TopScoresFragment();
        fragmentScores.setListener(mapListener);
        fragmentScores.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frameScores, fragmentScores)
                .commit();
        fragmentMaps= new MapsFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.frameMap, fragmentMaps).commit();
    }


    ClickListener mapListener = new ClickListener() {
        @Override
        public void onScoreClick(double lat, double lon) {
            GoogleMap gm = fragmentMaps.getmMap();
            LatLng point = new LatLng(lon, lat);
            gm.addMarker(new MarkerOptions()
                    .position(point)
                    .title("    " ));
            gm.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 13.0f));        }
    };
}