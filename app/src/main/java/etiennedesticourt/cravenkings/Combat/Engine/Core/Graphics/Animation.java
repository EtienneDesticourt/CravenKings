package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class Animation implements Graphics {
    private int currentFrameIndex;
    private int frameSizeX, frameSizeY;
    private int numFrameX, numFrameY;
    private int numFrameTotal;
    private int offsetX, offsetY;
    private boolean looping;
    private int frameId;
    private Rect frameRect;

    public Animation(int frameSizeX, int frameSizeY, int numFrameX,
                     int numFrameY, int numFrameTotal, boolean looping, int frameId,
                     int offsetX, int offsetY) {
        this.currentFrameIndex = 0;
        this.frameSizeX = frameSizeX;
        this.frameSizeY = frameSizeY;
        this.numFrameX = numFrameX;
        this.numFrameY = numFrameY;
        this.numFrameTotal = numFrameTotal;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.looping = looping;
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
        currentFrameIndex += 1;
        if (currentFrameIndex >= numFrameTotal){
            if (looping){
                currentFrameIndex = 0;
            }
            else{
                currentFrameIndex = numFrameTotal;
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
