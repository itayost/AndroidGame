package com.example.obstaclerace.UI;

import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MovingObject {
    private ImageView img;


    public MovingObject(ImageView img) {
        this.img = img;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    // Method to move ImageView to the left
    public static void moveImageViewLeft(ImageView imageView, int leftMargin) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(leftMargin, layoutParams.topMargin, 0, 0);
        imageView.setLayoutParams(layoutParams);
    }

    // Method to move ImageView to the right
    public static void moveImageViewRight(ImageView imageView, int rightMargin) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(0, layoutParams.topMargin, rightMargin, 0);
        imageView.setLayoutParams(layoutParams);
    }

    public static void moveImageViewDown(ImageView imageView, int bottomMargin) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, 0, layoutParams.rightMargin, bottomMargin);
        imageView.setLayoutParams(layoutParams);
    }
}
