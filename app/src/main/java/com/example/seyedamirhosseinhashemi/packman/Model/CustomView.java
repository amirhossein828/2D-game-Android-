package com.example.seyedamirhosseinhashemi.packman.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.*;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seyedamirhosseinhashemi.packman.MainActivity;
import com.example.seyedamirhosseinhashemi.packman.R;

import java.util.Random;

/**
 * Created by seyedamirhosseinhashemi on 2017-05-28.
 */

public class CustomView extends View implements View.OnTouchListener {
    // declare the object that you animate
    private Packman circle,circle1,circle2,circle3;
    Packman [] arrayOfCircles;
    private float nextXPosC;
    private float nextYPosC;
    private Rectangle rectangle;
    public boolean isfailed;
    public int numberOfFailed;
    public int numberofCorrect;
    private boolean isTouched;
    private Context context;
    private float canvasWitdh,canvasHeight;
    private int lengthOfRectangle;
    android.view.animation.Animation animation;

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getLengthOfRectangle() {
        return lengthOfRectangle;
    }

    public void setLengthOfRectangle(int lengthOfRectangle) {
        this.lengthOfRectangle = lengthOfRectangle;
    }

    // add the constructor
    public CustomView(Context context) {
        super(context);
        this.context = context;

        isfailed = false;
        isTouched = false;

            Bitmap circleBitmap =
                    BitmapFactory.decodeResource(getResources(), R.drawable.circle);
            Bitmap image = Bitmap.createScaledBitmap(circleBitmap, 100, 100, true);

            Bitmap rectangleBitmap =
                    BitmapFactory.decodeResource(getResources(), R.drawable.rectangle);

            lengthOfRectangle = 400;
            Bitmap rectangleBtn = Bitmap.createScaledBitmap(rectangleBitmap,lengthOfRectangle,100,true);

        createCircles(image);
        arrayOfCircles = new Packman[]{circle, circle1, circle2, circle3};
        rectangle = new Rectangle(200f,1336f,rectangleBtn);
        setOnTouchListener(this);
    }

    public void createCircles(Bitmap image) {
        circle = new Packman(100f, 100f, image, WallHorizontal.LEFT, WallVertical.TOP);
        circle.setSpeed(10);
        circle1 = new Packman(400f, 600f, image,WallHorizontal.LEFT,WallVertical.TOP);
        circle1.setSpeed(20);
        circle2 = new Packman(50f, 600f, image,WallHorizontal.LEFT,WallVertical.TOP);
        circle2.setSpeed(15);
        circle3 = new Packman(0f, 0f, image,WallHorizontal.LEFT,WallVertical.TOP);
        circle3.setSpeed(5);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasWitdh = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        for (int i=0;i<4;i++) {
            float xPosC = arrayOfCircles[i].getxPosition();
            float yPosC = arrayOfCircles[i].getyPosition();
            canvas.drawBitmap(arrayOfCircles[i].getBitmapImage(), xPosC, yPosC, null);
        }

        float xPosR= rectangle.getxPosition();
        float yPosR= rectangle.getyPosition();
        canvas.drawBitmap(rectangle.getBitmapImage(),xPosR,yPosR,null);
    }

    public void move(){

            for (int j=0;j<4;j++) {
                // collision with right wall
                if (arrayOfCircles[j].getxPosition() >= canvasWitdh - 100) {
                    arrayOfCircles[j].setWallHorizontal(WallHorizontal.RIGHT);
                    isTouched = false;
                }
                // collision with left wall
                if (arrayOfCircles[j].getxPosition() <= 0) {
                    arrayOfCircles[j].setWallHorizontal(WallHorizontal.LEFT);
                    isTouched = false;
                }
                // collision with top wall
                if (arrayOfCircles[j].getyPosition() <= 0) {
                    arrayOfCircles[j].setWallVertical(WallVertical.TOP);
                    isTouched = false;
                }
                // collision with rectangle
                if (arrayOfCircles[j].getxPosition() >= rectangle.getxPosition() - 100
                        && arrayOfCircles[j].getxPosition() <= rectangle.getxPosition() + lengthOfRectangle && arrayOfCircles[j].getWallHorizontal() == WallHorizontal.RIGHT) {
                    // collision with surface of rectangle
                    if (arrayOfCircles[j].getyPosition() >= canvasHeight - 200 && arrayOfCircles[j].getyPosition() <= canvasHeight - 180) {
                        arrayOfCircles[j].setWallVertical(WallVertical.BUTTON);
                        CorrectCollision();
                    }
                    // collision with right edge of rectangle
                    if (arrayOfCircles[j].getyPosition() >= canvasHeight - 180) {
                        arrayOfCircles[j].setWallHorizontal(WallHorizontal.LEFT);
                        isTouched = false;
                    }

                } else if (arrayOfCircles[j].getxPosition() + 100 >= rectangle.getxPosition()
                        && arrayOfCircles[j].getxPosition() <= rectangle.getxPosition() + lengthOfRectangle && arrayOfCircles[j].getWallHorizontal() == WallHorizontal.LEFT) {
                    // collision with surface of rectangle
                    if (arrayOfCircles[j].getyPosition() >= canvasHeight - 200 && arrayOfCircles[j].getyPosition() <= canvasHeight - 130) {
                        arrayOfCircles[j].setWallVertical(WallVertical.BUTTON);
                        CorrectCollision();
                    }
                    // collision with right edge of rectangle
                    if (arrayOfCircles[j].getyPosition() >= canvasHeight - 130)
                    arrayOfCircles[j].setWallHorizontal( WallHorizontal.RIGHT);
                // cross the button of view
                } else if (arrayOfCircles[j].getyPosition() >= canvasHeight
                        &&
                        (arrayOfCircles[j].getxPosition() <= rectangle.getxPosition()
                                || arrayOfCircles[j].getxPosition() >= rectangle.getxPosition() + 400)) {
                    isfailed = true;
                    numberOfFailed++;
                    startAgain(arrayOfCircles[j],100f,100f);
                }
                direction(arrayOfCircles[j], j);
                arrayOfCircles[j].setxPosition(nextXPosC);
                arrayOfCircles[j].setyPosition(nextYPosC);
            }

    }
    // update ui with new correct answer (for set view in main thread we should
    //come back to main thread, because we are not in main thread now)
    private void CorrectCollision() {
        if(isTouched == false) {
            numberofCorrect++;

            setPointTextViewInUI();
            isTouched = true;
        }
    }

    private void setPointTextViewInUI() {
        ((MainActivity)context).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                animation = AnimationUtils.loadAnimation(context, R.anim.animation_object);
                TextView textView = (TextView) ((MainActivity)context).findViewById(R.id.point);
                textView.setText(String.valueOf(numberofCorrect));
                textView.startAnimation(animation);
            }
        });
    }
    // start the app from the desired point in view
    private void startAgain(Packman packman,float x,float y) {
            packman.setxPosition(x);
            packman.setyPosition(y);
            packman.setWallHorizontal(WallHorizontal.LEFT);
            packman.setWallVertical(WallVertical.TOP);
    }

        public void direction(Packman packman,int h){
            if (packman.getWallHorizontal() == WallHorizontal.RIGHT) {
                nextXPosC = packman.getxPosition() - ((h+1)*packman.getSpeed());
            }
            if (packman.getWallHorizontal() == WallHorizontal.LEFT) {
                nextXPosC = packman.getxPosition() + ((h+1)*packman.getSpeed());
            }
            if (packman.getWallVertical() == WallVertical.TOP) {
                nextYPosC = packman.getyPosition() + ((h+1)*packman.getSpeed());
            }
            if (packman.getWallVertical() == WallVertical.BUTTON) {
                nextYPosC = packman.getyPosition() - ((h+1)*packman.getSpeed());
            }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        float canvasXpos = motionEvent.getX();

        Log.d("TOUCH",String.valueOf(canvasXpos));
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            if (canvasXpos > lengthOfRectangle/2 && canvasXpos < (canvasWitdh - lengthOfRectangle/2)) {
                rectangle.setxPosition(canvasXpos,lengthOfRectangle/2);
            } else if (canvasXpos < lengthOfRectangle/4) {
                rectangle.setxPosition((lengthOfRectangle/2),lengthOfRectangle);
            } else if (canvasXpos > (canvasWitdh - lengthOfRectangle)) {
                rectangle.setxPosition((canvasWitdh - lengthOfRectangle/2),lengthOfRectangle);
            }
        }

        return true;
    }
    // reset the app
    public void reset(){
        startAgain(arrayOfCircles[0],100f,100f);
        startAgain(arrayOfCircles[1],400f,600f);
        startAgain(arrayOfCircles[2],50f,600f);
        startAgain(arrayOfCircles[3],0f,0f);
    }

}
