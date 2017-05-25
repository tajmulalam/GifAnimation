package com.example.sumon.videosplash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class WelcomeActivity extends AppCompatActivity {
    ImageView ivMonster, ivFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivMonster = (ImageView) findViewById(R.id.ivMonster);
        ivFood = (ImageView) findViewById(R.id.ivFood);
        playGif();
    }

    private void playGif() {
        Glide.with(this)

                .load(R.drawable.ic_eating)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(ivMonster);

        Glide.with(this)
                .load(R.drawable.ic_food)
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(ivFood);

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
