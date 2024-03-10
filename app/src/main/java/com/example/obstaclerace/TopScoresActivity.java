package com.example.obstaclerace;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TopScoresActivity extends AppCompatActivity implements ClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_scores);
    }

    @Override
    public void onScoreClick(int scoreIndex) {

    }
}