package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import java.util.concurrent.ConcurrentHashMap;

public class EntityMover extends Object2DHandler {

    public EntityMover(ConcurrentHashMap<Entity, Boolean> entities, int delay) {
        super(entities, delay, "Entity Mover");
    }

    @Override
    protected void act(Object2D object){
        Entity entity = (Entity) object;
        if (entity.isMoving()){
            entity.move();
        }
    }
}
