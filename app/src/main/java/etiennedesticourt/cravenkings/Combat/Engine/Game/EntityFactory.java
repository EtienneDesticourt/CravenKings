package etiennedesticourt.cravenkings.Combat.Engine.Game;

import android.content.Context;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Animation;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityState;
import etiennedesticourt.cravenkings.Map.Allegiance;
import etiennedesticourt.cravenkings.R;

public class EntityFactory {
    private static final int KNIGHT_SPEED = 10;
    private static final int KNIGHT_MAX_LIFE = 10;
    private static final int KNIGHT_STRENGTH = 10;
    private static final int KNIGHT_RANGE = 1;

    private static final int ARCHER_SPEED = 1;
    private static final int ARCHER_MAX_LIFE = 10;
    private static final int ARCHER_STRENGTH = 10;
    private static final int ARCHER_RANGE = 1;

    private static final int MAGE_SPEED = 1;
    private static final int MAGE_MAX_LIFE = 10;
    private static final int MAGE_STRENGTH = 10;
    private static final int MAGE_RANGE = 1;

    public static Entity genKnight(AnimationManager manager, Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim   = manager.get(R.raw.knight_walk_animation,   R.drawable.knight_walk_frames);
        Animation attackAnim = manager.get(R.raw.knight_attack_animation, R.drawable.knight_attack_frames);
        Animation deathAnim  = manager.get(R.raw.knight_death_animation,  R.drawable.knight_death_frames);
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        return new Entity(spawn.getSpawn(), movementSide * KNIGHT_SPEED, KNIGHT_MAX_LIFE,
                KNIGHT_STRENGTH,
                KNIGHT_RANGE,
                EntityType.KNIGHT,
                spawn.getAllegiance(),
                animations);
    }

    public static Entity genArcher(AnimationManager manager, Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim   = manager.get(R.raw.archer_walk_animation,   R.drawable.archer_walk_frames);
        Animation attackAnim = manager.get(R.raw.archer_attack_animation, R.drawable.archer_attack_frames);
        Animation deathAnim  = manager.get(R.raw.archer_death_animation,  R.drawable.archer_death_frames);
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        return new Entity(spawn.getSpawn(), movementSide * ARCHER_SPEED, ARCHER_MAX_LIFE,
                ARCHER_STRENGTH,
                ARCHER_RANGE,
                EntityType.ARCHER,
                spawn.getAllegiance(),
                animations);
    }

    public static Entity genMage(AnimationManager manager, Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim   = manager.get(R.raw.mage_walk_animation,   R.drawable.mage_walk_frames);
        Animation attackAnim = manager.get(R.raw.mage_attack_animation, R.drawable.mage_attack_frames);
        Animation deathAnim  = manager.get(R.raw.mage_death_animation,  R.drawable.mage_death_frames);
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        return new Entity(spawn.getSpawn(), movementSide * MAGE_SPEED, MAGE_MAX_LIFE,
                MAGE_STRENGTH,
                MAGE_RANGE,
                EntityType.MAGE,
                spawn.getAllegiance(),
                animations);
    }

    private static int getMovementSide(Allegiance allegiance){
        if (allegiance == Allegiance.PLAYER){
            return 1;
        }
        return -1;
    }
}
