package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Projectile extends Entity{

    public Projectile(int[] spawn, int speedX, int strength, int rangeX, Enum type,
                      Allegiance allegiance,
                      HashMap<EntityState, ? extends Graphics> graphics){
        super(spawn, speedX, 1, strength,  rangeX, 1, 1, type, allegiance, graphics);
        setCantBeAttacked();
    }

    public void attack(Entity entity){
        entity.damage(getStrength());
        die();
    }
}
