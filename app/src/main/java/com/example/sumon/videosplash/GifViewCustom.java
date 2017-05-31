package com.example.sumon.videosplash;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.net.Uri;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Rakib on 5/31/2017.
 */

public class GifViewCustom extends View {
    private InputStream mInputStream;
    private Movie mMovie;
    private int mWidth, mHeight;
    private long mStart;
    private Context mContext;
    GifAnimateListener gifAnimateListener;

    public GifViewCustom(Context context) {
        super(context);
        this.mContext = context;
    }

    public GifViewCustom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GifViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        if (attrs.getAttributeName(1).equals("background")) {
            int id = Integer.parseInt(attrs.getAttributeValue(1).substring(1));
            setGifImageResource(id);
        }
    }

    public void setGifAnimateListener(GifAnimateListener gifAnimateListener) {
        this.gifAnimateListener = gifAnimateListener;
    }

    private void init() {
        setFocusable(true);
        mMovie = Movie.decodeStream(mInputStream);
        mWidth = mMovie.width();
        mHeight = mMovie.height();

        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isPaused) {

            long now = SystemClock.uptimeMillis();

            if (mStart == 0) {
                mStart = now;
            }

            if (mMovie != null) {

                int duration = mMovie.duration();
                if (duration == 0) {
                    duration = 100;
                }

                int relTime = (int) ((now - mStart) % duration);

                mMovie.setTime(relTime);
                mMovie.draw(canvas, 0, 0);
                if (relTime==mMovie.duration()){
                    gifAnimateListener.animationFinishListener();
                }
                invalidate();
            }
        } else {

            if (mMovie != null) {
                mMovie.setTime(0);
                mMovie.draw(canvas, 0, 0);
                invalidate();
            }
        }
    }

    boolean isPaused = false;

    public void setPause(boolean state) {
        this.isPaused = state;
        invalidate();
    }

    public void setGifImageResource(int id) {
        mInputStream = mContext.getResources().openRawResource(id);
        init();
    }

    public void setGifImageUri(Uri uri) {
        try {
            mInputStream = mContext.getContentResolver().openInputStream(uri);
            init();
        } catch (FileNotFoundException e) {
            Log.e("GIfImageView", "File not found");
        }
    }

    public interface GifAnimateListener {
        public void animationFinishListener();
    }
}
