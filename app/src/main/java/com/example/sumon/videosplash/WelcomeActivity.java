package com.example.sumon.videosplash;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import pl.droidsonroids.gif.AnimationListener;
import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {
    GifImageView ivMonster, ivFood;
    String sound_1 = "gif_eating.mp3";
    String sound_2 = "gif_first_jingle.mp3";
    String sound_3 = "gif_got_a_grass.mp3";
    private final SensorEventListener LightSensorListener
            = new SensorEventListener(){

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() == Sensor.TYPE_LIGHT){
                textLIGHT_reading.setText("LIGHT: " + event.values[0]);
            }
        }

    };


    TextView textLIGHT_reading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivMonster = (GifImageView) findViewById(R.id.ivMonster);
        ivFood = (GifImageView) findViewById(R.id.ivFood);

        textLIGHT_reading=(TextView) findViewById(R.id.textLIGHT_reading);

        SensorManager mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);

        Sensor LightSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if(LightSensor != null){
            // textLIGHT_available.setText("Sensor.TYPE_LIGHT Available");
            mySensorManager.registerListener(
                    LightSensorListener,
                    LightSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);

        }else{
            // textLIGHT_available.setText("Sensor.TYPE_LIGHT NOT Available");
        }

        try {
            final GifDrawable gifIdle= new GifDrawable(getResources(), R.drawable.idle);
            final GifDrawable gifrecognized = new GifDrawable(getResources(), R.drawable.recognized);
            final GifDrawable gifFood_a = new GifDrawable(getResources(), R.drawable.food_up_a);
            final GifDrawable gifFood_b = new GifDrawable(getResources(), R.drawable.food_up_b);
            final GifDrawable gifchewing = new GifDrawable(getResources(), R.drawable.chewing2);
            final GifDrawable gifmagic_a = new GifDrawable(getResources(), R.drawable.magic_a);
            final GifDrawable gifmagic_b = new GifDrawable(getResources(), R.drawable.magic_b);
            final GifDrawable gifStrawberry = new GifDrawable(getResources(), R.drawable.strawerry);

           // gifFood_b.setLoopCount(1);
            //gifFromResourceMons.setLoopCount(1);
           // gifFromResourceFood.setLoopCount(1);
           // gifChwing.setLoopCount(1);
           // gifHappy.setLoopCount(1);
           // float speed=0.5f;
            //gifHappy.setSpeed(speed);
            //gifChwing.setSpeed(speed);
            //gifFromResourceFood.setSpeed(speed);

            ivMonster.setImageDrawable(gifIdle);
            //ivFood.setImageDrawable(gifFromResourceFood);


            Log.e("Mir happy:",String.valueOf(gifIdle.getDuration()));
            Log.e("Mir chew:",String.valueOf(gifIdle.getDuration()));
            Log.e("Mir food:",String.valueOf(gifIdle.getDuration()));

            //gifFromResource.stop();
            // gifFromResourceMons.setLoopCount(1);
            gifIdle.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel","1");

                    //if(loopNumber>5){
                        gifIdle.stop();
                        gifIdle.reset();
                        ivMonster.setImageDrawable(gifrecognized);
                  //  }
                }

            });
            gifrecognized.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel","2");
                    gifrecognized.stop();
                    gifrecognized.reset();

                    ivMonster.setImageDrawable(gifFood_a);
                    gifFood_b.reset();
                    ivFood.setImageDrawable(gifFood_b);
                    ivFood.setVisibility(View.VISIBLE);
                }

            });
            gifFood_a.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel","3");
                    //gifFood_b.stop();
                   // gifFood_b.reset();
                    ivMonster.setImageDrawable(gifchewing);


                }

            });
            gifFood_b.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel:","4");
                   // gifFood_b.reset();
                    gifFood_b.stop();
                    ivFood.setVisibility(View.GONE);
                }

            });
            gifchewing.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel","5");
                    /*if(last_one_loop<last_one_loopMax)
                        last_one_loop++;
                    else {*/
                        last_one_loop = 0;
                        gifchewing.stop();
                        gifchewing.reset();
                        ivMonster.setImageDrawable(gifmagic_a);
                        gifmagic_b.reset();
                        ivFood.setImageDrawable(gifmagic_b);
                        ivFood.setVisibility(View.VISIBLE);
                    }
                //}

            });
            gifmagic_a.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel","6");
                    gifmagic_a.stop();
                    gifmagic_a.reset();
                    ivMonster.setImageDrawable(gifStrawberry);
                }

            });
            gifmagic_b.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel","7");
                    gifmagic_b.stop();
                    gifmagic_b.reset();
                    ivFood.setVisibility(View.GONE);
                   // ivMonster.setImageDrawable(gifStrawberry);
                }

            });
            gifStrawberry.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    Log.e("hel: "+String.valueOf(loopNumber),"8");
                    if(last_one_loop<last_one_loopMax)
                        last_one_loop++;
                    else {
                        last_one_loop=0;
                        gifStrawberry.stop();
                        gifStrawberry.reset();
                        ivMonster.setImageDrawable(gifIdle);
                    }
                }

            });

            //  gifFromResourceFood.setLoopCount(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

int last_one_loop=0;
    int last_one_loopMax=1;

    int chewingloop=0;


    public void playAgain(View view) {
        startNextActivity();
    }

    /// method that used to play sound
    MediaPlayer mp;
    public void playBeep(String soundFile) {
        mp = new MediaPlayer();
        try {

            AssetFileDescriptor descriptor = getAssets().openFd(soundFile);
            mp.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            mp.prepare();
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


/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ivMonster = (GifImageView) findViewById(R.id.ivMonster);
        ivFood = (GifImageView) findViewById(R.id.ivFood);

        try {
            final GifDrawable gifFromResourceMons = new GifDrawable(getResources(), R.drawable.ic_eating);
            final GifDrawable gifFromResourceFood = new GifDrawable(getResources(), R.drawable.ic_food);
            final GifDrawable gifChwing = new GifDrawable(getResources(), R.drawable.chewing);
            final GifDrawable gifHappy = new GifDrawable(getResources(), R.drawable.happy);

            //gifFromResourceMons.setLoopCount(1);
            // gifFromResourceFood.setLoopCount(1);
            // gifChwing.setLoopCount(1);
            // gifHappy.setLoopCount(1);


            ivMonster.setImageDrawable(gifFromResourceMons);
            ivFood.setImageDrawable(gifFromResourceFood);

            //gifFromResource.stop();
            // gifFromResourceMons.setLoopCount(1);
            gifFromResourceMons.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    //Toast.makeText(WelcomeActivity.this,"sada",Toast.LENGTH_LONG).show();
                    gifFromResourceMons.stop();
                    ivMonster.setImageDrawable(gifChwing);
                    gifChwing.start();
                    gifFromResourceFood.stop();
                    ivFood.setVisibility(View.INVISIBLE);

                    Log.e("hel","1");

                }
            });
            gifFromResourceFood.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    //Toast.makeText(WelcomeActivity.this,"sada",Toast.LENGTH_LONG).show();
                    Log.e("hel","2");
                }

            });
            gifChwing.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    //Toast.makeText(WelcomeActivity.this,"sada",Toast.LENGTH_LONG).show();
                    gifChwing.stop();
                    ivMonster.setImageDrawable(gifHappy);
                    gifHappy.start();
                    Log.e("hel","3");
                }
            });
            gifHappy.addAnimationListener(new AnimationListener() {
                @Override
                public void onAnimationCompleted(int loopNumber) {
                    //Toast.makeText(WelcomeActivity.this,"sada",Toast.LENGTH_LONG).show();
                    gifHappy.stop();
                    ivMonster.setImageDrawable(gifFromResourceMons);
                    gifFromResourceMons.start();
                    ivFood.setImageDrawable(gifFromResourceFood);
                    gifFromResourceFood.start();
                    ivFood.setVisibility(View.VISIBLE);
                    Log.e("hel","4");
                }
            });
            //  gifFromResourceFood.setLoopCount(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



}
