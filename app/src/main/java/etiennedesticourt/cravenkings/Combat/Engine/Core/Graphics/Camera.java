package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Camera implements View.OnTouchListener, Runnable{
    private int x,y;
    private float velocityX, velocityY;
    private double friction;
    private boolean running;
    private boolean interruptFlinging;
    private final int DELAY = 10;
    private GestureDetectorCompat mDetector;

    public Camera(Context context){
        x = 0;
        y = 0;
        velocityX = 0;
        velocityY = 0;
        friction = 0.95;
        mDetector = new GestureDetectorCompat(context, new MyGestureListener());
    }

    public void move(){
        x += velocityX;
        y += velocityY;
        velocityX = (int) (velocityX * friction);
        velocityY = (int) (velocityY * friction);
    }

    public void stopFling(){
        interruptFlinging = true;
        while (running){
        }
        velocityX = 0;
        velocityY = 0;
    }

    public void fling(float velocityX){
        if (running){
            stopFling();
        }
        this.velocityX = velocityX / 10;
        new Thread(this).start();
    }

    @Override
    public void run() {
        interruptFlinging = false;
        running = true;
        while ((velocityX != 0 || velocityY != 0) && !interruptFlinging){
            move();

            try {
                Thread.sleep(DELAY);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        running = false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.mDetector.onTouchEvent(motionEvent);
    }

    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            stopFling();
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            x += distanceX;
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (velocityX < 600 && velocityX > -600){ return false; }
            fling(-velocityX);
            return true;
        }
    }

    public void setX(int x){
        this.x = x;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
