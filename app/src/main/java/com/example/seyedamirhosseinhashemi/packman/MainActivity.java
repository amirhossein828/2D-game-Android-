package com.example.seyedamirhosseinhashemi.packman;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.seyedamirhosseinhashemi.packman.Model.Animation;
import com.example.seyedamirhosseinhashemi.packman.Model.CustomView;
import com.example.seyedamirhosseinhashemi.packman.Model.Packman;
import com.example.seyedamirhosseinhashemi.packman.Model.Rectangle;


public class MainActivity extends AppCompatActivity implements View.OnClickListener ,SeekBar.OnSeekBarChangeListener{


    LinearLayout linMid;
    ImageButton btnStart,btnStop,pointBtn,restart;
    CustomView customView;
    Animation animation;
    boolean stopIsClicked;
    SeekBar seekBar;
    TextView point;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }



    private void initialize() {

        seekBar = (SeekBar)findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(this);
        linMid = (LinearLayout)findViewById(R.id.linMid);
        btnStart = (ImageButton) findViewById(R.id.btnStart);
        btnStop = (ImageButton)findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this);
        restart = (ImageButton)findViewById(R.id.restart);
        restart.setOnClickListener(this);

        point = (TextView)findViewById(R.id.point);

        btnStop.setOnClickListener(this);
        customView = new CustomView(this);
        linMid.addView(customView);

        stopIsClicked = true;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.btnStart :
                if (stopIsClicked == true) {
                    animation = new Animation(customView);
                    customView.isfailed = false;
                    customView.numberOfFailed = 0;
                    stopIsClicked = false;
                    animation.start();
                }
                break;
            case R.id.btnStop :

                stopIsClicked = true;
                animation.stop();
                break;
            case R.id.restart :
                customView.numberofCorrect = 0;
                point.setText(String.valueOf(customView.numberofCorrect));
                customView.reset();

                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if(stopIsClicked == true) {
            Bitmap rectangleBitmap =
                    BitmapFactory.decodeResource(getResources(), R.drawable.rectangle);
            customView.setLengthOfRectangle(i * 8);
            Bitmap rectangleBtn = Bitmap.createScaledBitmap(rectangleBitmap, customView.getLengthOfRectangle(), 100, true);

            Rectangle rectangle = new Rectangle(200f, 1336f, rectangleBtn);
            customView.setRectangle(rectangle);
            animation = new Animation(customView);
            animation.startRecThread();

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
