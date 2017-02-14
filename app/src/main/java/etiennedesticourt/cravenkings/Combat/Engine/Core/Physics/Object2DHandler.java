package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import android.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Utils.ThreadProfiler;

public abstract class Object2DHandler extends Thread{
    private final String DEBUG_TAG = "ENTITY_HANDLER";
    private ConcurrentHashMap<Integer, ? extends Object2D> objects;
    private final int delay;
    private boolean stop = false;
    private final String handlerName;
    private ThreadProfiler threadProfiler;

    public Object2DHandler(ConcurrentHashMap<Integer, ? extends Object2D> map, int delay, String handlerName){
        this.objects = map;
        this.delay = delay;
        this.handlerName = handlerName;
        this.threadProfiler = new ThreadProfiler(handlerName);
    }

    public void run(){
        Log.d(DEBUG_TAG, "Thread for " + handlerName + " started.");
        while (!stop){
            threadProfiler.setIterationStart();
            for (Map.Entry<Integer, ? extends Object2D> entry : objects.entrySet()) {
                Object2D o = entry.getValue();
                act(o);
            }

            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            threadProfiler.setIterationStop();
        }
        Log.d(DEBUG_TAG, "Thread for " + handlerName + " stopped.");
    }

    protected abstract void act(Object2D object);

    public void end(){
        stop = true;
    }

    public ConcurrentHashMap<Integer, ? extends Object2D> getObjects(){
        return objects;
    }
}
