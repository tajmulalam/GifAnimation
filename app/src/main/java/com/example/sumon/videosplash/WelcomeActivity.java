package com.example.sumon.videosplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    GifImageView ivMonster, ivFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivMonster = (GifImageView) findViewById(R.id.ivMonster);
        ivFood = (GifImageView) findViewById(R.id.ivFood);
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
