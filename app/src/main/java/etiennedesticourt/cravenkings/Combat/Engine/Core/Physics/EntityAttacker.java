package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class EntityAttacker extends Object2DHandler {

    public EntityAttacker(ConcurrentHashMap<Entity, Boolean> entities, int delay) {
        super(entities, delay, "Entity Attacker");
    }

    @Override
    protected void act(Object2D object) {
        Entity entity = (Entity) object;
        Entity target = null;

        //We select the enemy entity with lowest hp that's in attack range
        Iterator<Entity> it = (Iterator<Entity>) getObjects().iterator();
        while (it.hasNext()){
            Entity otherEntity = it.next();
            if (!entity.isInSameTeam(otherEntity)
                    && entity.isInMyRange(otherEntity)
                    && entity.canBeAttacked()){
                if (target == null || target.getLife() > otherEntity.getLife()){
                    target = otherEntity;
                }
            }
        }

        if (target != null){
            entity.startOrContinueAttacking();
            entity.attack(target);
        }
        else{
            entity.stopAttacking();
        }
    }
}
