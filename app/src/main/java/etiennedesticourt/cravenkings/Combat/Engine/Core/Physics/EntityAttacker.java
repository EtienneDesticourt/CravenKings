package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import android.util.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EntityAttacker extends Object2DHandler {

    public EntityAttacker(ConcurrentHashMap<Integer, Entity> entities, int delay) {
        super(entities, delay, "Entity Attacker");
    }

    @Override
    protected void act(Object2D object) {
        Entity entity = (Entity) object;
        if (entity.isDead()) {
            return;
        }
        Entity target = null;

        //We select the enemy entity with lowest hp that's in attack range
        for (Map.Entry<Integer, ? extends Object2D> entry : getObjects().entrySet()) {
            Entity otherEntity = (Entity) entry.getValue();
            if (!entity.isInSameTeam(otherEntity)
                    && entity.isInMyRange(otherEntity)
                    && otherEntity.canBeAttacked()
                    && !otherEntity.isDead()){
                if (target == null || target.getLife() > otherEntity.getLife()){
                    target = otherEntity;
                }
            }
        }

        if (target != null){
            Log.d("ATTACKER", entity.getIdentification() + " took for target " + target.getIdentification());
            entity.startOrContinueAttacking();
            entity.attack(target);
        }
        else{
            Log.d("ATTACKER", entity.getIdentification() + " has no target.");
            entity.stopAttacking();
        }
    }
}
