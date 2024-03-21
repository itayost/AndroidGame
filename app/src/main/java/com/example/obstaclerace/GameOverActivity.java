package com.example.obstaclerace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Comparator;

public class GameOverActivity extends AppCompatActivity {
    private TextView result;
    private EditText user_input;
    private MaterialButton buttonSaveScore;
    private String playerName;
    private int score = 0;
    private GPSTracker gpsService;
    private InternalDB internalDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        result = findViewById(R.id.textViewScore);
        user_input = findViewById(R.id.editTextPlayerName);
        buttonSaveScore = findViewById(R.id.buttonSaveScore);
        clicked();

        score = getIntent().getIntExtra("score", 0);
        result.setText("Score:" + score);
    }

    private void clicked(){
        buttonSaveScore.setOnClickListener(view ->{
            playerName = user_input.getText().toString();

            gpsService = new GPSTracker(GameOverActivity.this);
            if (gpsService.canGetLocation()) {
                gpsService.getLocationMutableLiveData()
                        .observe(this, new Observer<Location>() {
                            @Override
                            public void onChanged(Location location) {
                                if(location != null) {
                                    double latitude = location.getLatitude();
                                    double longitude = location.getLongitude();
                                    // * End of Location Service
                                    user_input.setVisibility(View.INVISIBLE);
                                    saveScore(playerName, score, longitude, latitude);
                                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                                    gpsService.removeLocationObservers(GameOverActivity.this);
                                    finish();
                                }
                            }
                        });
            }
            else
            {
                gpsService.showSettingsAlert();
            }
        });
    }

    private void saveScore(String name, int score, double longitude, double latitude) {
        Score s = new Score(name, score);
        s.setLon(longitude);
        s.setLat(latitude);
        String js = MSPV3.getMe().getString("InternalDB", "");
        internalDB = new Gson().fromJson(js, InternalDB.class);
        internalDB.getRecords().add(s);
        internalDB.getRecords().sort(new SortByScore());
        String json = new Gson().toJson(internalDB);
        MSPV3.getMe().putString("InternalDB", json);
        checkScores();
    }

    private void checkScores(){
        for (Score score : internalDB.getRecords()) {
            Log.d("ScoreSaving", "Player: " + score.getName() + ", Score: " + score.getScore() + ", Lon: " + score.getLon() + ", Lat: " + score.getLat());
        }
        Log.d("ScoreSaving", "Saved scores: " + internalDB.getRecords().size());
    }
}


class SortByScore implements Comparator<Score> {
    @Override
    public int compare(Score rec1, Score rec2) {
        return rec2.getScore() - rec1.getScore();
    }
}