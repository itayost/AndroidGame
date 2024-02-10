package com.example.obstaclerace.UI;

import android.content.Context;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.LinearLayoutCompat;

public class GameView extends SurfaceView implements Runnable {
    final int rows = 12;
    final int cols = 3;
    final int lives = 3;

    private boolean isPlaying;
    private Thread gameThread = null;
    private MovingObject[][] board = new MovingObject[rows][cols];
    private LinearLayoutCompat lives;
    private RelativeLayout game;

    public GameView(Context context) {
        super(context);
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

    private void update() {
        // Update game state (player position, obstacle positions)
    }

    private void draw() {
        // Draw the player, obstacles, and other game elements
    }

    private void control() {
        // Control frame rate
        try {
            gameThread.sleep(17); // roughly 60 frames per second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void playerMoveLeft(){

    }
}
