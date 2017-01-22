package etiennedesticourt.cravenkings.Combat.Engine.Game.Units;

import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityState;
import etiennedesticourt.cravenkings.Combat.Engine.Game.EntityType;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Knight extends Entity {
    public static final int SPEED = 10;
    public static final int MAX_LIFE = 10;
    public static final int STRENGTH = 10;
    public static final int RANGE = 1;
    public static final int COST = 1;

    public Knight(int[] spawn, int speed, Allegiance allegiance,
                  HashMap<EntityState, ? extends Graphics> graphics) {
        super(spawn, speed, MAX_LIFE, STRENGTH, RANGE, EntityType.KNIGHT, allegiance, graphics);
    }
}