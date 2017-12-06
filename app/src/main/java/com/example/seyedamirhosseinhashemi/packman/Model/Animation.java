
package com.example.seyedamirhosseinhashemi.packman.Model;

import android.util.Log;

import com.example.seyedamirhosseinhashemi.packman.Model.CustomView;


/**
 * Created by moh on 24/05/2017.
 */

public class Animation implements Runnable{

    CustomView customView;
    Thread thread;
    int sleepDelay=100;
    boolean startIsClicked;

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }


    public int getSleepDelay() {
        return sleepDelay;
    }

    public void setSleepDelay(int sleepDelay) {
        this.sleepDelay = sleepDelay;
    }

    public Animation(CustomView customView) {
        this.customView = customView;
        startIsClicked = false;
        thread = new Thread(this);
    }

    @Override
    public void run() {

            while (thread!=null && startIsClicked){
            switch (customView.numberofCorrect){
                case 0 :
                    sleepDelay = 100;
                    break;
                case 6 :
                    sleepDelay = 90;
                    break;
                case 10 :
                    sleepDelay =80;
                    break;
                case 14 :
                    sleepDelay =70;
                    break;
                case 20 :
                    sleepDelay =60;
                    break;
            }
            customView.move();

            // redraw the canvas
            // or call the method onDraw
            customView.postInvalidate();
            try {
                thread.sleep(sleepDelay);
            } catch (InterruptedException e) {
                Log.d("ANIMATION",e.getMessage());
            }
        }

        if(startIsClicked != true){
            customView.postInvalidate();
        }

    }


    public void start(){
        startIsClicked = true;

        thread.start();
    }

    public void stop(){
        thread=null;

    }

    public void startRecThread(){
        if(startIsClicked==false)
        thread.start();
    }



}