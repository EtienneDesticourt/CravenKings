package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import android.util.Log;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Object2DHandler extends Thread{
    private final String DEBUG_TAG = "ENTITY_HANDLER";
    private Set<? extends Object2D> objects;
    private final int delay;
    private boolean stop = false;
    private final String handlerName;

    public Object2DHandler(ConcurrentHashMap<Entity, Boolean> map, int delay, String handlerName){
        this.objects = Collections.newSetFromMap(map);
        this.delay = delay;
        this.handlerName = handlerName;
    }

    public void run(){
        Log.d(DEBUG_TAG, "Thread for " + handlerName + " started.");
        while (!stop){
            Iterator<? extends Object2D> it = objects.iterator();
            while (it.hasNext()){
                act(it.next());
            }

            try {
                Thread.sleep(delay);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Log.d(DEBUG_TAG, "Thread for " + handlerName + " stopped.");
    }

    protected abstract void act(Object2D object);

    public void end(){
        stop = true;
    }

    public Set<? extends Object2D> getObjects(){
        return objects;
    }
}
