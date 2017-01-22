package etiennedesticourt.cravenkings.Combat.Engine.Game;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.ParserConfigurationException;

import etiennedesticourt.cravenkings.BR;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.Entity;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityAttacker;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityCleaner;
import etiennedesticourt.cravenkings.Combat.Engine.Core.Physics.EntityMover;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Archer;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Knight;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Mage;
import etiennedesticourt.cravenkings.Combat.Engine.Game.Units.Miner;
import etiennedesticourt.cravenkings.Map.Allegiance;

public class Player extends BaseObservable {
    private final int MINER_COST = 10; //TODO: Find more appropriate spot
    private Spawn spawn;
    private ConcurrentHashMap<Entity, Boolean> entities;
    private EntityMover mover;
    private EntityAttacker attacker;
    private EntityCleaner cleaner;
    private int miners;
    private int money;
    private Timer moneyTimer;

    public Player(Context context, Allegiance allegiance){
        entities = new ConcurrentHashMap<>();
        spawn = new Spawn(context, allegiance);
        money = 300;
        miners = 0;
        moneyTimer = new Timer();
    }

    public void startEntityHandlers(){
        mover = new EntityMover(entities, 200);
        attacker = new EntityAttacker(entities, 1000);
        cleaner  = new EntityCleaner(entities, 1000);
        mover.start();
        attacker.start();
        cleaner.start();
        moneyTimer.scheduleAtFixedRate(new UpdateMoneyTask(), 0, Miner.INCOME_INTERVAL_MILLISECONDS);
    }

    public void stopEntityHandlers() {
        mover.end();
        attacker.end();
        cleaner.end();
        moneyTimer.cancel();
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

    public boolean spawnMage() throws IOException, SAXException, ParserConfigurationException {
        if (money >= Mage.COST) {
            Entity mage = EntityFactory.genMage(spawn);
            addEntity(mage);
            decreaseMoneyBy(Mage.COST);
            return true;
        }
        return false;
    }

    public boolean spawnArcher() throws IOException, SAXException, ParserConfigurationException {
        if (money >= Archer.COST) {
            Entity archer = EntityFactory.genArcher(spawn);
            addEntity(archer);
            decreaseMoneyBy(Archer.COST);
            return true;
        }
        return false;
    }

    public boolean spawnKnight() throws IOException, SAXException, ParserConfigurationException {
        if (money >= Knight.COST) {
            Entity knight = EntityFactory.genKnight(spawn);
            addEntity(knight);
            decreaseMoneyBy(Knight.COST);
            return true;
        }
        return false;
    }

    public boolean spawnMiner() {
        if (money >= Miner.COST) {
            addMiner();
            decreaseMoneyBy(Miner.COST);
            return true;
        }
        return false;
    }

    @Bindable
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
        notifyPropertyChanged(BR.money);
    }

    public void decreaseMoneyBy(int cost) {
        setMoney(money - cost);
    }

    public void increaseMoneyBy(int income) {
        setMoney(money + income);
    }

    private class UpdateMoneyTask extends TimerTask {
        @Override
        public void run() {
            int income = miners * Miner.INCOME;
            increaseMoneyBy(income);
        }
    }


    @Bindable
    public int getMiners() {
        return miners;
    }

    public void addMiner() {
        miners += 1;
        notifyPropertyChanged(BR.miners);
    }
}
