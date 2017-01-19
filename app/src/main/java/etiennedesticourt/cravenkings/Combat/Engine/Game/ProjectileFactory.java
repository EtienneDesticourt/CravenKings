package etiennedesticourt.cravenkings.Combat.Engine.Game;

import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityState;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Projectile;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class ProjectileFactory {
    private int speed;
    private int strength;
    private int range;
    private HashMap< EntityState, ? extends Graphics > graphics;

    public ProjectileFactory(int strength, int speed, int range,
                             HashMap<EntityState, ? extends Graphics> graphics){
        this.strength = strength;
        this.speed = speed;
        this.range = range;
        this.graphics = graphics;
    }

    public void spawnProjectile(int x, int y, Allegiance allegiance){
        int[] spawn = new int[2];
        spawn[0] = x;
        spawn[1] = y;
        new Projectile(spawn, speed, strength, range, EntityType.OTHER, allegiance, graphics);
    }
}
