package com.example.sumon.videosplash;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        h = new Handler();
        videoView = (VideoView) findViewById(R.id.videoView);
        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_clip);
        videoView.setVideoURI(video);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                startNextActivity();
            }
        });

        videoView.start();
        Runnable r = new Runnable() {

            @Override
            public void run() {

                if (videoView != null) {

                    if (videoView.isPlaying()) {

                        videoView.pause();
                    } else {
                        videoView.start();
                    }
                }

                h.postDelayed(this, 50);
            }
        };

        h.postDelayed(r, 200);

    }

    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, WelcomeActivity.class));
        finish();
    }
}
