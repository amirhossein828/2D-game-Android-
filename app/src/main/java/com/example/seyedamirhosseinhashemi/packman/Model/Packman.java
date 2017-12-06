package com.example.seyedamirhosseinhashemi.packman.Model;

import android.graphics.Bitmap;

/**
 * Created by seyedamirhosseinhashemi on 2017-05-28.
 */

public class Packman {


    private float xPosition;
    private float yPosition;
    private Bitmap bitmapImage;
    private WallHorizontal wallHorizontal;
    private int speed;

    public Packman(float xPosition, float yPosition, Bitmap bitmapImage,
                   WallHorizontal wallHorizontal, WallVertical wallVertical) {
        this.xPosition = xPosition - 50;
        this.yPosition = yPosition - 50;
        this.bitmapImage = bitmapImage;
        this.wallHorizontal = wallHorizontal;
        this.wallVertical = wallVertical;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public WallHorizontal getWallHorizontal() {
        return wallHorizontal;
    }

    public void setWallHorizontal(WallHorizontal wallHorizontal) {
        this.wallHorizontal = wallHorizontal;
    }

    public WallVertical getWallVertical() {
        return wallVertical;
    }

    public void setWallVertical(WallVertical wallVertical) {
        this.wallVertical = wallVertical;
    }

    private WallVertical wallVertical;

    private int xDirection = 1;
    private int yDirection = 1;




    public float getxPosition() {
        return xPosition;
    }

    public void setxPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public float getyPosition() {
        return yPosition;
    }

    public void setyPosition(float yPosition) {
        this.yPosition = yPosition;
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
