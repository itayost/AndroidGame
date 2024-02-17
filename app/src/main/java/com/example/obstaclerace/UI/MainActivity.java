package com.example.obstaclerace.UI;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.obstaclerace.R;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private static final int ROWS = 5;
    private static final int COLS = 3;

    private static final int LIVES = 3;
    private AppCompatImageButton Game_BTN_left, Game_BTN_right;
    private ImageView[] Game_IMG_player;
    private ImageView[][] Game_IMG_eggs = new ImageView[ROWS][COLS];
    private ImageView[] Game_IMG_hearts = new ImageView[LIVES];

    private int chicken_place = 1;

    private int hits = 0;

    public static final int DELAY = 1000;
    public static final long milisec = 500;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGame();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTimer();
    }

    private void initGame(){
        findViews();
        setButtonsListeners();
    }

    private void findViews() {
        Game_BTN_left = findViewById(R.id.game_BTN_left);
        Game_BTN_right = findViewById(R.id.game_BTN_right);
        findPlayer();
        findHearts();
        findEggs();
    }

    private void findPlayer(){
        Game_IMG_player = new ImageView[]{
                findViewById(R.id.game_IMG_PlayerLeft),
                findViewById(R.id.game_IMG_PlayerCenter),
                findViewById(R.id.game_IMG_PlayerRight)
        };
    }

    private void findHearts() {
        for (int i = 0; i < LIVES; i++) {
            int heartId = getResources().getIdentifier("main_IMG_heart" + (i + 1), "id", getPackageName());
            ShapeableImageView heart = findViewById(heartId);
            Game_IMG_hearts[i] = heart;
        }
    }

    private void findEggs(){
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                int viewId = getResources().getIdentifier("game_IMG_egg" + ((i * COLS) + j + 1), "id", getPackageName());
                ImageView egg = findViewById(viewId);
                Game_IMG_eggs[i][j] = egg;
            }
        }
    }

    private void setButtonsListeners(){
        Game_BTN_left.setOnClickListener(v ->{
            if (chicken_place > 0){
                Game_IMG_player[chicken_place].setVisibility(View.INVISIBLE);
                chicken_place--;
                Game_IMG_player[chicken_place].setVisibility(View.VISIBLE);
            }
        });

        Game_BTN_right.setOnClickListener(v ->{
            if (chicken_place < COLS - 1){
                Game_IMG_player[chicken_place].setVisibility(View.INVISIBLE);
                chicken_place++;
                Game_IMG_player[chicken_place].setVisibility(View.VISIBLE);
            }
        });
    }

    Runnable runnable = new Runnable() {
        public void run() {
            handler.postDelayed(this, DELAY);
            continueGame();
        }
    };

    private void continueGame() {
        moveEggs();
        addEgg();
    }

    private void addEgg(){
        int randomColumn = (int) (Math.random() * COLS);
        Game_IMG_eggs[0][randomColumn].setVisibility(View.VISIBLE);
    }

    private void moveEggs(){
        for (int i = 0; i < COLS; i ++){
            if (Game_IMG_eggs[ROWS - 1][i].getVisibility() == View.VISIBLE){
                Game_IMG_eggs[ROWS - 1][i].setVisibility(View.INVISIBLE);
                tryToHit(i);
            }
        }

        for (int i = ROWS - 2; i >= 0; i--){
            for (int j = 0; j < COLS; j ++){
                if(Game_IMG_eggs[i][j].getVisibility() == View.VISIBLE){
                    Game_IMG_eggs[i][j].setVisibility(View.INVISIBLE);
                    Game_IMG_eggs[i + 1][j].setVisibility((View.VISIBLE));
                }
            }
        }
    }

    private void tryToHit(int col) {
        if(col == chicken_place){
            vibrate();
            Game_IMG_hearts[hits].setVisibility(View.INVISIBLE);
            hits++;
            if(hits == LIVES){
                gameOver();
            }
        }
    }

    private void gameOver() {
        for(int i = 0; i < LIVES; i++){
            Game_IMG_hearts[i].setVisibility(View.VISIBLE);
            hits = 0;
        }
    }

    private void startTimer() {
        handler.postDelayed(runnable, DELAY);
    }

    private void stopTimer() {
        handler.removeCallbacks(runnable);
    }


    private void vibrate () {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        }
}