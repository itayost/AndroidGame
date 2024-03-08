package com.example.obstaclerace.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.obstaclerace.R;
import com.google.android.material.button.MaterialButton;

public class MenuActivity extends AppCompatActivity {

    private MaterialButton menu_BTN_SlowButton, menu_BTN_FastButton, menu_BTN_sensor, menu_BTN_highScores;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        findView();
        clicked();
    }

    private void findView() {
        menu_BTN_SlowButton = findViewById(R.id.menu_BTN_slowbutton);
        menu_BTN_FastButton = findViewById(R.id.menu_BTN_fastbutton);
        menu_BTN_sensor = findViewById(R.id.menu_BTN_sensor);
        menu_BTN_highScores = findViewById(R.id.menu_BTN_highScores);
    }

    private void clicked() {
        menu_BTN_SlowButton.setOnClickListener(view -> startGame(false,true,false));
        menu_BTN_FastButton.setOnClickListener(view -> startGame(false,false,true));
        menu_BTN_sensor.setOnClickListener(view -> startGame(true,false,false));
        menu_BTN_highScores.setOnClickListener(view -> Scores());
    }

    public void startGame(boolean sensorMode,boolean slowMode,boolean FastMode) {
        Intent gameIntent = new Intent(this, MainActivity.class);

        Bundle bundle = new Bundle();
        bundle.putBoolean(MainActivity.SENSOR_MODE, sensorMode);
        bundle.putBoolean(MainActivity.SLOW_MODE, slowMode);
        bundle.putBoolean(MainActivity.FAST_MODE, FastMode);

        gameIntent.putExtras(bundle);
        startActivity(gameIntent);
    }
    public void Scores() {
        //Intent ScoreIntent = new Intent(this, Top10Activity.class);
        //startActivity(ScoreIntent);
    }

}