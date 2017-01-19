package etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics;

import android.graphics.Canvas;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Object2D;

public class Renderer {
    private ArrayList<Background> backgrounds;
    private Set<? extends Object2D> object2Ds;
    private Camera camera;

    public Renderer(ConcurrentHashMap<? extends Object2D, Boolean> map, Camera camera){
        object2Ds = Collections.newSetFromMap(map);
        backgrounds = new ArrayList<>();
        this.camera = camera;
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

        //Draw objects
        Iterator<? extends Object2D> it = object2Ds.iterator();
        while (it.hasNext()){
            Object2D obj = it.next();

            Graphics g = obj.getGraphics();
            if (g == null){
                continue;
            }
            g.draw(obj.getX(), obj.getY(), canvas, camera);
        }
    }
}
