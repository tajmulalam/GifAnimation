package com.example.sumon.videosplash;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    GifImageView ivMonster, ivFood;
    String sound_1 = "gif_eating.mp3";
    String sound_2 = "gif_first_jingle.mp3";
    String sound_3 = "gif_got_a_grass.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivMonster = (GifImageView) findViewById(R.id.ivMonster);
        ivFood = (GifImageView) findViewById(R.id.ivFood);

        try {
            final GifDrawable gifFromResourceMons = new GifDrawable(getResources(), R.drawable.greenbar);
            ivMonster.setImageDrawable(gifFromResourceMons);

            final GifDrawable gifFromResourceFood = new GifDrawable(getResources(), R.drawable.redbar);
            ivFood.setImageDrawable(gifFromResourceFood);

            //gifFromResource.stop();
            // gifFromResourceMons.setLoopCount(1);
            gifFromResourceMons.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    //Toast.makeText(WelcomeActivity.this,"sada",Toast.LENGTH_LONG).show();
                }
            });
            //  gifFromResourceFood.setLoopCount(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void playAgain(View view) {
        startNextActivity();
    }

    /// method that used to play sound
    MediaPlayer mp;
    public void playBeep(String soundFile) {
        try {
            if (mp.isPlaying()) {
                mp.stop();
                mp.release();
                mp = new MediaPlayer();
            }

            AssetFileDescriptor descriptor = getAssets().openFd(soundFile);
            mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            mp.prepare();
            mp.setVolume(1f, 1f);
            mp.setLooping(true);
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
