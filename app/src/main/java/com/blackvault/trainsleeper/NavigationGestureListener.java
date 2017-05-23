package com.blackvault.trainsleeper;

import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by Blackvault on 22/05/2017.
 */

public class NavigationGestureListener extends SimpleOnGestureListener {

    private Context mCurrentContext;

    public NavigationGestureListener(Context aBaseContext) {
        mCurrentContext = aBaseContext;
    }

    @Override
    public boolean onFling(MotionEvent aEventOne, MotionEvent aEventTwo,
                           float aVelocityX, float aVeloctiyY) {

        Toast.makeText(mCurrentContext, "onFling", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public void onLongPress(MotionEvent aEvent) {
        Toast.makeText(mCurrentContext, "onLongPress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Toast.makeText(mCurrentContext, "onDoubleTap", Toast.LENGTH_SHORT).show();
        return true;
    }
}