package com.example.obstaclerace.UI;

import android.content.Context;
import android.widget.ImageView;

import com.example.obstaclerace.R;

public class Player extends MovingObject {
    // Player properties like position, bitmap, etc.

    public Player (Context context){
        super(new ImageView(context));
        getImg().setImageResource(R.drawable.chicken);
    }

    public void update() {
        // Update player position based on user input
    }


}
