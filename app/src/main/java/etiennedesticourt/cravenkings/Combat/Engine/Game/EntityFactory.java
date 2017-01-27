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
        Animation walkAnim, attackAnim, deathAnim;
        if (spawn.getAllegiance() == Allegiance.PLAYER) {
            walkAnim   = manager.get(R.raw.knight_walk_animation);
            attackAnim = manager.get(R.raw.knight_attack_animation);
            deathAnim  = manager.get(R.raw.knight_death_animation);
        }
        else {
            walkAnim   = manager.get(R.raw.knight_walk_animation_enemy);
            attackAnim = manager.get(R.raw.knight_attack_animation_enemy);
            deathAnim  = manager.get(R.raw.knight_death_animation_enemy);
        }
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        int height = walkAnim.getOffsetY() + walkAnim.getFrameSizeY();
        int[] spawnCoords = offsetSpawnByHeight(walkAnim, spawn);

        return new Knight(spawnCoords, height, movementSide * Knight.SPEED, spawn.getAllegiance(), animations);
    }

    public static Entity genArcher(Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        AnimationManager manager = AnimationManager.INSTANCE;
        int movementSide = getMovementSide(spawn.getAllegiance());


        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim, attackAnim, deathAnim;
        if (spawn.getAllegiance() == Allegiance.PLAYER) {
            walkAnim   = manager.get(R.raw.archer_walk_animation);
            attackAnim = manager.get(R.raw.archer_attack_animation);
            deathAnim  = manager.get(R.raw.archer_death_animation);
        }
        else {
            walkAnim   = manager.get(R.raw.archer_walk_animation_enemy);
            attackAnim = manager.get(R.raw.archer_attack_animation_enemy);
            deathAnim  = manager.get(R.raw.archer_death_animation_enemy);
        }
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        int height = walkAnim.getOffsetY() + walkAnim.getFrameSizeY();
        int[] spawnCoords = offsetSpawnByHeight(walkAnim, spawn);

        return new Archer(spawnCoords, height, movementSide * Archer.SPEED, spawn.getAllegiance(), animations);
    }

    public static Entity genMage(Spawn spawn)
            throws ParserConfigurationException, SAXException, IOException {
        AnimationManager manager = AnimationManager.INSTANCE;
        int movementSide = getMovementSide(spawn.getAllegiance());

        HashMap<EntityState, Animation> animations = new HashMap<>();
        Animation walkAnim, attackAnim, deathAnim;
        if (spawn.getAllegiance() == Allegiance.PLAYER) {
            walkAnim   = manager.get(R.raw.mage_walk_animation);
            attackAnim = manager.get(R.raw.mage_attack_animation);
            deathAnim  = manager.get(R.raw.mage_death_animation);
        }
        else {
            walkAnim   = manager.get(R.raw.mage_walk_animation_enemy);
            attackAnim = manager.get(R.raw.mage_attack_animation_enemy);
            deathAnim  = manager.get(R.raw.mage_death_animation_enemy);
        }
        animations.put(EntityState.MOVING, walkAnim);
        animations.put(EntityState.ATTACKING, attackAnim);
        animations.put(EntityState.DEAD, deathAnim);

        int height = walkAnim.getOffsetY() + walkAnim.getFrameSizeY();
        int[] spawnCoords = offsetSpawnByHeight(walkAnim, spawn);

        return new Mage(spawnCoords, height, movementSide * Mage.SPEED, spawn.getAllegiance(), animations);
    }

    private static int getMovementSide(Allegiance allegiance){
        if (allegiance == Allegiance.PLAYER){
            return 1;
        }
        return -1;
    }

    private static int[] offsetSpawnByHeight(Animation anim, Spawn spawn) {
        int spawnOffsetY = anim.getOffsetY() + anim.getFrameSizeY();
        int[] coords = spawn.getSpawn();
        int[] newCoords = new int[]{coords[0], coords[1] - spawnOffsetY};
        return newCoords;
    }
}
