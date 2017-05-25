package com.example.sumon.videosplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    GifImageView ivMonster, ivFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivMonster = (GifImageView) findViewById(R.id.ivMonster);
        ivFood = (GifImageView) findViewById(R.id.ivFood);

        try {
            final GifDrawable gifFromResourceMons = new GifDrawable( getResources(), R.drawable.greenbar);
            ivMonster.setImageDrawable(gifFromResourceMons);

            final GifDrawable gifFromResourceFood = new GifDrawable( getResources(), R.drawable.redbar);
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

    private void startNextActivity() {
        if (isFinishing())
            return;
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
