package com.blackvault.trainsleeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by Blackvault on 22/05/2017.
 */

public class SwipeNavigationGesture implements OnGestureListener {

    private Context mCurrentContext;

    public SwipeNavigationGesture(Context aBaseContext) {
        mCurrentContext = aBaseContext;
    }

    @Override
    public boolean onFling(MotionEvent aEventOne, MotionEvent aEventTwo,
                           float aVelocityX, float aVelocityY) {

        Toast.makeText(mCurrentContext, "onFling", Toast.LENGTH_SHORT).show();

        if (aEventOne.getX() < aEventTwo.getX()) {
            Toast.makeText(mCurrentContext, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
            return true;

        }

        else {
            Toast.makeText(mCurrentContext, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent aEvent) {

    }
}