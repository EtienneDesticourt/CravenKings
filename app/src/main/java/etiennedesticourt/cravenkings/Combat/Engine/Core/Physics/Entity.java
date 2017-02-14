package etiennedesticourt.cravenkings.Combat.Engine.Core.Physics;

import java.util.HashMap;

import etiennedesticourt.cravenkings.Combat.Engine.Core.Graphics.Graphics;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Entity implements Object2D{
    private static int count = 0;
    private int id;
    private int x;
    private int dx;
    private int direction;
    private int rangeX;
    private final int y;
    private int width, height;
    private int maxLife, life;
    private int strength;
    private Enum type;
    private EntityState state;
    private Allegiance allegiance;
    private HashMap<EntityState, ? extends Graphics> graphics;
    //Used so units don't stop to attack projectiles
    private boolean canBeAttacked = true;


    public Entity(int[] spawn, int speedX, int maxLife, int strength, int rangeX,
                  int width, int height,
                  Enum type,
                  Allegiance allegiance,
                  HashMap<EntityState, ? extends Graphics> graphics){
        this.x = spawn[0];
        this.y = spawn[1];
        this.dx = speedX;
        this.direction = Integer.signum(dx);
        this.rangeX = rangeX;
        this.maxLife = maxLife;
        this.life = maxLife;
        this.strength = strength;
        this.type = type;
        this.state = EntityState.MOVING;
        this.allegiance = allegiance;
        this.graphics = graphics;
        this.width = width;
        this.height = height;
        id = count;
        count += 1;
    }

    public void move(){
        this.x += dx;
    }

    public void attack(Entity entity){
        entity.damage(strength);
    }

    public void damage(int damage){
        life -= damage;
        if (life <= 0){
            die();
        }
    }

    public void kill(){
        this.life = 0;
        die();
    }

    public void startOrContinueAttacking(){
        state = EntityState.ATTACKING;
    }

    public void stopAttacking(){
        state = EntityState.MOVING;
    }

    public boolean isDead(){
        return state == EntityState.DEAD;
    }

    public boolean isInSameTeam(Entity entity){
        return allegiance == entity.allegiance;
    }

    public boolean isMoving(){
        return state == EntityState.MOVING;
    }

    public boolean isInMyRange(Entity entity){
        return entity.getX() <= x + rangeX * direction && entity.getX() + entity.getWidth() >= x + rangeX * direction;
    }

    public int getStrength() {
        return strength;
    }

    public boolean canBeAttacked() {
        return canBeAttacked;
    }

    public void setCantBeAttacked() {
        canBeAttacked = false;
    }

    public int getLife(){
        return life;
    }

    public int getId() { return id; }

    @Override
    public int getX(){
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Graphics getGraphics() {
        return graphics.get(state);
    }

    public String getIdentification() {
        return String.format("%s#%d(%s) of %s", type.toString(), id, state.toString(), allegiance.toString());
    }

    public void die(){
        state = EntityState.DEAD;
    }
}
