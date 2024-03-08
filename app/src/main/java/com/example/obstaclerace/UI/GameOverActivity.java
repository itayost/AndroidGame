package com.example.obstaclerace.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.obstaclerace.R;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;

import java.util.Comparator;

public class GameOverActivity extends AppCompatActivity {
    private TextView result;
    private EditText user_input;
    private MaterialButton buttonSaveScore;

    private int score = 0;

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
            Score s = new Score(user_input.getText().toString(), score);
            saveScore(s);
            finish();
        });
    }

    private void saveScore(Score s) {
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
            Log.d("ScoreSaving", "Player: " + score.getName() + ", Score: " + score.getScore());
        }

        String json = new Gson().toJson(internalDB);
        MSPV3.getMe().putString("InternalDB", json);

        // Retrieve the saved scores and verify
        String savedJson = MSPV3.getMe().getString("InternalDB", "");
        InternalDB savedDB = new Gson().fromJson(savedJson, InternalDB.class);
        Log.d("ScoreSaving", "Saved scores: " + savedDB.getRecords().size());
    }
}


class SortByScore implements Comparator<Score> {
    @Override
    public int compare(Score rec1, Score rec2) {
        return rec2.getScore() - rec1.getScore();
    }
}