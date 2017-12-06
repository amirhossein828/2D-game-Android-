package com.example.seyedamirhosseinhashemi.packman.Model;

import android.graphics.Bitmap;

/**
 * Created by seyedamirhosseinhashemi on 2017-05-28.
 */

public class Rectangle {


    private float xPosition;
    private float yPosition;
    private Bitmap bitmapImage;

    private int xDirection = 1;
    private int yDirection = 1;



    public Rectangle(float xPosition, float yPosition, Bitmap bitmapImage) {
        this.xPosition = xPosition - 200 ;
        this.yPosition = yPosition - 100 ;
        this.bitmapImage = bitmapImage;
    }


    public float getxPosition() {
        return xPosition ;
    }

    public void setxPosition(float xPosition,int l) {

        this.xPosition = xPosition -(l/2);
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition -100;
    }

    public int getxDirection() {
        return xDirection;
    }

    public void setxDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public int getyDirection() {
        return yDirection;
    }

    public void setyDirection(int yDirection) {
        this.yDirection = yDirection;
    }

    public Bitmap getBitmapImage() {
        return bitmapImage;
    }

    public void setBitmapImage(Bitmap bitmapImage) {
        this.bitmapImage = bitmapImage;
    }

    @Override
    public String toString() {
        return "X Position=" + xPosition + ", Y Position=" + yPosition;
    }
}
