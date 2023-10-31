package com.webfams.pankha;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {


    private float currentRotation = 0;
    private MediaPlayer soundPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView blades = findViewById(R.id.blades);
        soundPlayer = MediaPlayer.create(this, R.raw.switch_sound); // Load a sound file
        soundPlayer.setLooping(true);

        currentRotation += 30;

        Handler handler = new Handler();

        Switch offSwitch = findViewById(R.id.switchBtn);

        Runnable rotationRunnable = new Runnable() {
            @Override
            public void run() {
                currentRotation += 45;
                blades.setRotation(currentRotation);
                handler.postDelayed(this, 100);
            }
        };

        offSwitch.setOnClickListener(view -> {

            if (offSwitch.isChecked()) {
                handler.post(rotationRunnable);
                playSwitchSound();
            } else {
                handler.removeCallbacks(rotationRunnable);
                stopSwitchSound();
            }
        });

    }

    private void stopSwitchSound() {
        if (soundPlayer != null && soundPlayer.isPlaying()) {
            soundPlayer.stop();
            soundPlayer.prepareAsync();
        }
    }

    private void playSwitchSound() {
        if (soundPlayer != null) {
            soundPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPlayer != null) {
            soundPlayer.release();
        }
    }
}