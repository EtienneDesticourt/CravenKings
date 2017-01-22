package etiennedesticourt.cravenkings.Combat.Engine.Game;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Animation;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityState;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Archer;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Knight;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Mage;
import etiennedesticourt.cravenkings.Map.Allegiance;
import etiennedesticourt.cravenkings.R;

public class EntityFactory {

    public static Entity genKnight(Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        AnimationManager manager = AnimationManager.INSTANCE;
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim   = manager.get(R.raw.knight_walk_animation,   R.drawable.knight_walk_frames);
        Animation attackAnim = manager.get(R.raw.knight_attack_animation, R.drawable.knight_attack_frames);
        Animation deathAnim  = manager.get(R.raw.knight_death_animation,  R.drawable.knight_death_frames);
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        return new Knight(spawn.getSpawn(), movementSide * Knight.SPEED, spawn.getAllegiance(), animations);
    }

    public static Entity genArcher(Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        AnimationManager manager = AnimationManager.INSTANCE;
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim   = manager.get(R.raw.archer_walk_animation,   R.drawable.archer_walk_frames);
        Animation attackAnim = manager.get(R.raw.archer_attack_animation, R.drawable.archer_attack_frames);
        Animation deathAnim  = manager.get(R.raw.archer_death_animation,  R.drawable.archer_death_frames);
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        return new Archer(spawn.getSpawn(), movementSide * Archer.SPEED, spawn.getAllegiance(), animations);
    }

    public static Entity genMage(Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        AnimationManager manager = AnimationManager.INSTANCE;
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim   = manager.get(R.raw.mage_walk_animation,   R.drawable.mage_walk_frames);
        Animation attackAnim = manager.get(R.raw.mage_attack_animation, R.drawable.mage_attack_frames);
        Animation deathAnim  = manager.get(R.raw.mage_death_animation,  R.drawable.mage_death_frames);
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        return new Mage(spawn.getSpawn(), movementSide * Mage.SPEED, spawn.getAllegiance(), animations);
    }

    private static int getMovementSide(Allegiance allegiance){
        if (allegiance == Allegiance.PLAYER){
            return 1;
        }
        return -1;
    }
}
