package com.example.obstaclerace.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.res.ResourcesCompat;

import com.example.obstaclerace.R;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {
    private static final int ROWS = 5;
    private static final int COLS = 5;
    private static final int MAX_LIVES = 3;

    private int lives = MAX_LIVES;
    private int chickenPlace;

    private gameLogic game;
    private Drawable egg;

    private Drawable heart;
    private AppCompatImageButton Game_BTN_left, Game_BTN_right;
    private ImageView[] Game_IMG_player;
    private ImageView[][] Game_IMG_eggs = new ImageView[ROWS][COLS];
    private ImageView[] Game_IMG_hearts = new ImageView[MAX_LIVES];

    private AppCompatTextView Score_Board;

    private SensorManager sensorManager;
    private Sensor sensor;
    private boolean sensorMode = false, slowMode = false, fastMode = false;
    public static final String SENSOR_MODE = "SENSOR_MODE", SLOW_MODE = "SLOW_MODE", FAST_MODE = "FAST_MODE";


    public static int DELAY = 1500;
    public static final long milisec = 500;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sensorMode = extras.getBoolean(SENSOR_MODE, false);
            slowMode = extras.getBoolean(SLOW_MODE, false);
            fastMode = extras.getBoolean(FAST_MODE, false);
        }
        if(slowMode){
            DELAY = 1500;
        }
        else if(fastMode){
            DELAY = 800;
        }
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
        game = new  gameLogic(ROWS, COLS, MAX_LIVES);
        chickenPlace = game.getChicken_place();
        findViews();
        updateChicken();
        setButtonsListeners();
    }

    private void findViews() {
        Game_BTN_left = findViewById(R.id.game_BTN_left);
        Game_BTN_right = findViewById(R.id.game_BTN_right);
        Score_Board = findViewById(R.id.main_LBL_score);
        findPlayer();
        findHearts();
        findEggs();
        findDrawables();
    }

    private void findDrawables() {
        egg = ResourcesCompat.getDrawable(getResources(), R.drawable.egg, null);
        heart = ResourcesCompat.getDrawable(getResources(), R.drawable.heart, null);
    }

    private void findPlayer(){
        Game_IMG_player = new ImageView[]{
                findViewById(R.id.game_IMG_Player1),
                findViewById(R.id.game_IMG_Player2),
                findViewById(R.id.game_IMG_Player3),
                findViewById(R.id.game_IMG_Player4),
                findViewById(R.id.game_IMG_Player5)
        };
    }

    private void findHearts() {
        for (int i = 0; i < MAX_LIVES; i++) {
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
            if (game.movePlayer(-1)){
                updateChicken();
            }
        });

        Game_BTN_right.setOnClickListener(v ->{
            if (game.movePlayer(1)){
                updateChicken();
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
        game.nextMove();
        updateBoard();
        updateLives();
        updateScore();
        if(game.gameOver()){
            gameOver();
        }
    }

    private void updateLives(){
        if(this.lives != game.getLives()){
            vibrate();
            this.lives = game.getLives();
            for (int i = 0; i < MAX_LIVES; i++){
                if(i < this.lives){
                    Game_IMG_hearts[i].setVisibility(View.VISIBLE);
                }
                else{
                    Game_IMG_hearts[i].setVisibility(View.INVISIBLE);
                }
            }
        }
    }
    private void updateBoard() {
        for(int i = 0; i < ROWS; i ++){
            for(int j = 0; j < COLS; j ++){
                if(game.getBoard()[i][j] == 0){
                    Game_IMG_eggs[i][j].setVisibility(View.INVISIBLE);
                }
                else{
                    if(game.getBoard()[i][j] == 1){
                        Game_IMG_eggs[i][j].setImageDrawable(heart);
                    }
                    else if(game.getBoard()[i][j] == -1){
                        Game_IMG_eggs[i][j].setImageDrawable(egg);
                    }
                    Game_IMG_eggs[i][j].setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void updateChicken() {
        Game_IMG_player[chickenPlace].setVisibility(View.INVISIBLE);
        chickenPlace = game.getChicken_place();
        Game_IMG_player[chickenPlace].setVisibility(View.VISIBLE);
    }

    private void updateScore(){
        Score_Board.setText("Score: " + game.getScore());
    }

    private void gameOver() {
        Intent gameOverIntent = new Intent(MainActivity.this, GameOverActivity.class);
        gameOverIntent.putExtra("score", game.getScore());
        startActivity(gameOverIntent);
        this.finish();
        stopTimer();
        finish();
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