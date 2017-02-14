package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import java.util.concurrent.ConcurrentHashMap;

public class EntityManager {
    private ConcurrentHashMap<Integer, Entity> entities;
    private EntityMover mover;
    private EntityAttacker attacker;
    private EntityCleaner cleaner;

    public EntityManager() {
        entities = new ConcurrentHashMap<>(4, 0.9f, 1);
    }

    public void startEntityHandlers(){
        mover = new EntityMover(entities, 200);
        attacker = new EntityAttacker(entities, 1000);
        cleaner  = new EntityCleaner(entities, 1000);
        mover.start();
        attacker.start();
        //cleaner.start();
    }

    public void stopEntityHandlers() {
        mover.end();
        attacker.end();
        cleaner.end();
    }

    public void addEntity(Entity entity){
        entities.put(entity.getId(), entity);
    }

    public ConcurrentHashMap<Integer, Entity> getEntities(){
        return entities;
    }
}
