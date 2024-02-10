package com.example.obstaclerace.UI;


import android.content.Context;
import android.widget.ImageView;

import com.example.obstaclerace.R;

public class Obstacle extends MovingObject{
    // Obstacle properties like position, bitmap, etc.

    public Obstacle(Context context){
        super(new ImageView(context));
        getImg().setImageResource(R.drawable.egg);
    }

    public void update() {
        // Update obstacle position (moving towards the player)
    }
}
