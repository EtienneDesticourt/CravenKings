package etiennedesticourt.cravenkings.Combat.Engine.Game.Units;

import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityState;
import etiennedesticourt.cravenkings.Combat.Engine.Game.EntityType;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Castle extends Entity {
    public static final int SPEED = 20; //TODO: Load from res file
    public static final int MAX_LIFE = 50;
    public static final int STRENGTH = 10;
    public static final int RANGE = 1000;
    public static final int COST = 1;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 190;

    public Castle(int[] spawn, int height, int speed, Allegiance allegiance,
                  HashMap<EntityState, ? extends Graphics> graphics) {
        super(spawn, speed, MAX_LIFE, STRENGTH, RANGE, WIDTH, height, EntityType.CASTLE, allegiance, graphics);
    }
}
