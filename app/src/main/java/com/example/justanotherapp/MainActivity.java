package com.example.justanotherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer; // Used to access our media
    AudioManager audioManager; // Use to access phone audio settings

    public void playAudio(View view){
        mediaPlayer.start();
    }
    public void pauseAudio(View view){
        mediaPlayer.pause();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE); // audioManager setup done
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); // finding the max volume of phone in int
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); // finding the current phone volume

        mediaPlayer = MediaPlayer.create(this, R.raw.deathnote); // Setting up the audio we want to play
        SeekBar volumeControl = (SeekBar) findViewById(R.id.volumeSeekBar);

        volumeControl.setMax(maxVolume); // setting max value of seek bar
        volumeControl.setProgress(currentVolume); // setting the value of seek bar equal to phone when it starts
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("SeekBar CHanged", Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}