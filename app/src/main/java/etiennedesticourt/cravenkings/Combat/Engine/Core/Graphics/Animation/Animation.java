package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Animation;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.AssetHandler;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Camera;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;

public class Animation implements Graphics {
    private int currentFrameIndex;
    private int frameSizeX, frameSizeY;
    private int numFrameX, numFrameY;
    private int numFrameTotal;
    private int offsetX, offsetY;
    private boolean looping;
    private int frameId;
    private Rect frameRect;
    private boolean reversed;

    public Animation(int frameSizeX, int frameSizeY, int numFrameX,
                     int numFrameY, int numFrameTotal,
                     boolean looping, boolean reversed,
                     int frameId,
                     int offsetX, int offsetY) {
        this.frameSizeX = frameSizeX;
        this.frameSizeY = frameSizeY;
        this.numFrameX = numFrameX;
        this.numFrameY = numFrameY;
        this.numFrameTotal = numFrameTotal;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.looping = looping;
        this.reversed = reversed;
        if (reversed) {
            this.currentFrameIndex = numFrameTotal;
        }
        else {
            this.currentFrameIndex = 0;
        }
        this.frameId = frameId;
        frameRect = new Rect(0, 0, frameSizeX, frameSizeY);
    }

    public int getFrameSizeY() {
        return frameSizeY;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public Rect getCurrentFrameRect(){
        int row = currentFrameIndex / numFrameX;
        int column = currentFrameIndex - (row * numFrameX);
        frameRect.offsetTo(column * frameSizeX, row * frameSizeY);
        return frameRect;
    }

    public void next(){
        if (reversed) {
            backward();
        }
        else {
            forward();
        }
    }

    public void forward(){
        currentFrameIndex += 1;
        if (currentFrameIndex >= numFrameTotal){
            if (looping){
                currentFrameIndex = 0;
            }
            else{
                currentFrameIndex = numFrameTotal-1;
            }
        }
    }

    public void backward() {
        currentFrameIndex -= 1;
        if (currentFrameIndex < 0){
            if (looping){
                currentFrameIndex = numFrameTotal-1;
            }
            else{
                currentFrameIndex = 0;
            }
        }
    }

    @Override
    public void draw(int x, int y, Canvas c) {
        Rect src = getCurrentFrameRect();
        Rect dst = new Rect(x+offsetX, y+offsetY, x+offsetX+frameSizeX, y+offsetY+frameSizeY);
        Bitmap frames = AssetHandler.INSTANCE.get(frameId);
        c.drawBitmap(frames, src, dst, null);
    }

    @Override
    public void draw(int x, int y, Canvas c, Camera camera) {
        draw(x-camera.getX(), y-camera.getY(), c);
    }
}
