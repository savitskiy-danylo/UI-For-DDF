package Core;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity {
    private Entity enemy;
    private final int hpBase, dodgesMax, strengthMultiplier, agilityMultiplier, gachiPowerMax;
    private final int strengthBase, agilityBase, actionPointsBase, armorBase, damageBase;
    private int strengthGreen, agilityGreen, actionPointsGreen, armorGreen, damageGreen, gachiPower, hpMax, dodges, hp;
    private int money;
    protected final ArrayList<Buff> buffs = new ArrayList<>();
    protected Inventory inventory;
    protected boolean isDead = false;
    private String name;

    public Entity(int hpBase, int dodgesMax, int strengthMultiplier, int agilityMultiplier, int gachiPowerMax,
                  int strengthBase, int agilityBase, int actionPointsBase, int armorBase, int damageBase) {
        this.hpBase = hpBase;
        this.dodgesMax = dodgesMax;
        this.strengthMultiplier = strengthMultiplier;
        this.agilityMultiplier = agilityMultiplier;
        this.gachiPowerMax = gachiPowerMax;
        this.strengthBase = strengthBase;
        this.agilityBase = agilityBase;
        this.actionPointsBase = actionPointsBase;
        this.armorBase = armorBase;
        this.damageBase = damageBase;
        refreshDodges();
        refreshHpMax();
    }

    public abstract void nextTurn();

    private void refreshHpMax(){
        hpMax = (strengthBase + strengthGreen) * strengthMultiplier + hpBase;
    }

    private void refreshDodges(){
        dodges = (agilityBase + agilityGreen) * agilityMultiplier;
        if(dodges > dodgesMax) dodges = dodgesMax;
    }

    public Entity getEnemy() {
        return enemy;
    }

    public int getHp() {
        return hp;
    }

    public int getDodges() {
        return dodges;
    }

    public int getDodgesMax() {
        return dodgesMax;
    }

    public int getStrengthMultiplier() {
        return strengthMultiplier;
    }

    public int getAgilityMultiplier() {
        return agilityMultiplier;
    }

    public int getGachiPowerMax() {
        return gachiPowerMax;
    }

    public int getStrengthBase() {
        return strengthBase;
    }

    public int getAgilityBase() {
        return agilityBase;
    }

    public int getActionPointsBase() {
        return actionPointsBase;
    }

    public int getArmorBase() {
        return armorBase;
    }

    public int getDamageBase() {
        return damageBase;
    }

    public int getStrengthGreen() {
        return strengthGreen;
    }

    public int getAgilityGreen() {
        return agilityGreen;
    }

    public int getActionPointsGreen() {
        return actionPointsGreen;
    }

    public int getArmorGreen() {
        return armorGreen;
    }

    public int getDamageGreen() {
        return damageGreen;
    }

    public int getGachiPower() {
        return gachiPower;
    }

    public int getHpMax() {
        return hpMax;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean isDead() {

        return isDead;
    }

    public String getName() {
        return name;
    }

    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }

    public void setStrengthGreen(int strengthGreen) {
        this.strengthGreen = strengthGreen;
        refreshHpMax();
    }

    public void setAgilityGreen(int agilityGreen) {
        this.agilityGreen = agilityGreen;
        refreshDodges();
    }

    public void setActionPointsGreen(int actionPointsGreen) {
        this.actionPointsGreen = actionPointsGreen;
    }

    public void setArmorGreen(int armorGreen) {
        this.armorGreen = armorGreen;
    }

    public void setDamageGreen(int damageGreen) {
        this.damageGreen = damageGreen;
    }

    public void setGachiPower(int gachiPower) {
        this.gachiPower = gachiPower;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamage(int rawDamage){

        int damage = rawDamage - (armorBase + armorGreen);
        hp -= damage < 0 ? 0 : damage;
        if(hp < 1) isDead = true;
    }
    private static Random random = new Random();//TODO не забудь про статик
    /*public boolean tryDodge(){
        int result = random.nextInt(101);
        return
    }*/

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
