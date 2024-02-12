package com.example.obstaclerace.UI;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.example.obstaclerace.R;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private AppCompatImageButton Game_BTN_left, Game_BTN_right;
    private ImageView[] Game_IMG_player;
    private ImageView[][] Game_IMG_ios;
    private ShapeableImageView[] Game_IMG_hearts;
    private ShapeableImageView Space_IMG_background;

    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    private void vibrate() {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        v.vibrate(VibrationEffect.createOneShot(milisec, VibrationEffect.DEFAULT_AMPLITUDE));
    }
}