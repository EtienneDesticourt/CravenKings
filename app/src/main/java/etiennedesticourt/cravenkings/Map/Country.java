package etiennedesticourt.cravenkings.Map;

import java.io.Serializable;

public class Country implements Serializable{
    private Allegiance allegiance;
    private String name;
    private int troops;
    private int income;
    private int cost, time, strength, health;
    private int id;

    public Country(int id, String name, int troops, int income, Allegiance allegiance){
        this.id = id;
        this.name = name;
        this.troops = troops;
        this.income = income;
        this.allegiance = allegiance;
        cost = 0;
        time = 0;
        strength = 0;
        health = 0;
    }

    public Country(Allegiance allegiance){
        this.allegiance = allegiance;
    }

    public static Country getPlaceholderCountry(){
        return new Country(0, "Placeholder", 10, 20, Allegiance.COMPUTER);
    }

    public Boolean isFriendly(){
        return allegiance == Allegiance.PLAYER;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public Allegiance getAllegiance() {
        return allegiance;
    }

    public void setAllegiance(Allegiance allegiance) {
        this.allegiance = allegiance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTroops() {
        return troops;
    }

    public void setTroops(int troops) {
        this.troops = troops;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
