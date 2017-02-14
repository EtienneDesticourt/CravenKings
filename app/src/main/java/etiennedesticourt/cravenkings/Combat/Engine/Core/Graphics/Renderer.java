package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Object2D;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Utils.ThreadProfiler;

public class Renderer {
    private ArrayList<Background> backgrounds;
    private ConcurrentHashMap<Integer, ? extends Object2D> object2Ds;
    private Camera camera;
    private ThreadProfiler threadProfiler;
    private final boolean DEBUG = true;

    public Renderer(ConcurrentHashMap<Integer, ? extends Object2D> map, Camera camera){
        object2Ds = map;
        backgrounds = new ArrayList<>();
        this.camera = camera;
        String handlerName = "Renderer";
        this.threadProfiler = new ThreadProfiler(handlerName);
    }

    public void moveCamera(){
        //camera.setX(camera.getX() + 100);
    }

    public void addBackground(Background background){
        backgrounds.add(background);
    }

    public void draw(Canvas canvas){
        //Draw backgrounds
        /*Iterator<Background> backgroundIt = backgrounds.iterator();
        while (backgroundIt.hasNext()){
            backgroundIt.next().draw(canvas, camera);
        }*/
        Paint debugPaint;
        if (DEBUG) {
            debugPaint = new Paint();
            debugPaint.setColor(Color.rgb(0, 255, 0));
            debugPaint.setStrokeWidth(2);
            debugPaint.setStyle(Paint.Style.STROKE);
        }

        //Draw objects
        threadProfiler.setIterationStart();
        for (Map.Entry<Integer, ? extends Object2D> entry : object2Ds.entrySet()) {
            Object2D obj = entry.getValue();

            Graphics g = obj.getGraphics();
            if (g == null){
                continue;
            }
            g.draw(obj.getX(), obj.getY(), canvas, camera);

            if (DEBUG) {
                canvas.drawRect(obj.getX() - camera.getX(),
                        obj.getY() - camera.getY(),
                        obj.getX() + obj.getWidth() - camera.getX(),
                        obj.getY() + obj.getHeight() - camera.getY(),
                        debugPaint);
            }
        }
        threadProfiler.setIterationStop();
    }
}
