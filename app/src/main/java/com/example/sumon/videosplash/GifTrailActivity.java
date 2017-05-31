package com.example.sumon.videosplash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


public class GifTrailActivity extends AppCompatActivity implements GifViewCustom.GifAnimateListener {
    GifViewCustom mGifView;
    GifViewCustom mGifView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_trail);
        mGifView = (GifViewCustom) findViewById(R.id.gifImageView);
        mGifView.setGifImageResource(R.drawable.magic_a);
        mGifView.setGifAnimateListener(this);

        mGifView2 = (GifViewCustom) findViewById(R.id.gifImageView2);
        mGifView2.setGifImageResource(R.drawable.magic_b);
        mGifView2.setGifAnimateListener(this);


    }

    public void playGif(View view) {
        mGifView.setPause(false);

    }

    public void pauseGif(View view) {
        mGifView.setPause(true);

    }

    @Override
    public void animationFinishListener() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(GifTrailActivity.this, "finished", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
