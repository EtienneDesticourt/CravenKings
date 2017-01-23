package etiennedesticourt.cravenkings.Combat.Engine.Game.Units;

import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityState;
import etiennedesticourt.cravenkings.Combat.Engine.Game.EntityType;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Mage extends Entity {
    public static final int SPEED = 1;
    public static final int MAX_LIFE = 10;
    public static final int STRENGTH = 10;
    public static final int RANGE = 1;
    public static final int COST = 1;
    public static final int WIDTH = 80;
    public static final int HEIGHT = 210;

    public Mage(int[] spawn, int height, int speed, Allegiance allegiance,
                HashMap<EntityState, ? extends Graphics> graphics) {
        super(spawn, speed,  MAX_LIFE, STRENGTH, RANGE, WIDTH, height, EntityType.MAGE, allegiance, graphics);
    }
}
