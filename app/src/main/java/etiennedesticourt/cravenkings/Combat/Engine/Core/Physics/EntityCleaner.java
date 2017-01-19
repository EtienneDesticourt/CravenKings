package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import java.util.concurrent.ConcurrentHashMap;

public class EntityCleaner extends Object2DHandler {

    public EntityCleaner(ConcurrentHashMap<Entity, Boolean> map, int delay) {
        super(map, delay, "Entity Cleaner");
    }

    @Override
    protected void act(Object2D object) {
        Entity entity = (Entity) object;
        if (entity.isDead()){
            getObjects().remove(entity);
        }
    }
}
