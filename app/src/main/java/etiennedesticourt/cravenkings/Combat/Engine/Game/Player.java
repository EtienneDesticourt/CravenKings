package etiennedesticourt.cravenkings.Combat.Engine.Game;

import android.content.Context;

import java.util.concurrent.ConcurrentHashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityAttacker;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityCleaner;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityMover;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Player {
    private Spawn spawn;
    private ConcurrentHashMap<Entity, Boolean> entities;
    private int miners;
    private int money;

    public Player(Context context, Allegiance allegiance){
        entities = new ConcurrentHashMap<>();
        spawn = new Spawn(context, allegiance);
        money = 0;
        miners = 0;
    }

    public void startEntityHandlers(){
        EntityMover mover = new EntityMover(entities, 200);
        EntityAttacker attacker = new EntityAttacker(entities, 1000);
        EntityCleaner cleaner  = new EntityCleaner(entities, 1000);
        mover.start();
        attacker.start();
        cleaner.start();
    }

    public void addEntity(Entity entity){
        entities.put(entity, true);
    }

    public ConcurrentHashMap<Entity, Boolean> getEntities(){
        return entities;
    }

    public Spawn getSpawn(){
        return spawn;
    }
}
