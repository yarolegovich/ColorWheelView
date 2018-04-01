package com.yarolegovich.colorwheelshader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ColorWheelView colorWheel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorWheel = findViewById(R.id.color_wheel);

        SeekBar brightnessBar = findViewById(R.id.brightness_bar);
        brightnessBar.setOnSeekBarChangeListener(this);
        colorWheel.setBrightness(brightnessBar.getProgress());
    }

    @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        colorWheel.setBrightness(progress);
    }

    @Override public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
