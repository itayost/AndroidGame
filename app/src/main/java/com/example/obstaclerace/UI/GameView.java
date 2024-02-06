package com.example.obstaclerace.UI;

import android.content.Context;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {


    private boolean isPlaying;
    private Thread gameThread = null;

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

    // More methods will go here (e.g., for handling touch events)
}
