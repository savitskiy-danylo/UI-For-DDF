package GachiCore.Components;

import java.util.Random;

public class Stats {
    private final int damageBaseMin, damageBaseMax;
    private final int actionPointsBase;
    private final int strengthMaxBase;


    private int damageMin, damageMax, priceOfAttack, priceOfAttackBase;
    private int strength, strengthMax, agility, agilityMax;
    private int armor, armorMultiplier;
    private int actionPointsCurrent, actionPointsMax;

    private final Random random = new Random();

    public Stats(int damageBaseMin, int damageBaseMax, int actionPointsBase, int strengthMaxBase,
                 int priceOfAttackBase, int agilityMax, int armorMultiplier) {
        this.actionPointsBase = actionPointsBase;
        actionPointsMax = actionPointsBase;
        actionPointsCurrent = actionPointsBase;

        this.damageBaseMin = damageBaseMin;
        this.damageBaseMax = damageBaseMax;
        damageMin = damageBaseMin;
        damageMax = damageBaseMax;

        this.strengthMaxBase = strengthMaxBase;
        strengthMax = strengthMaxBase;
        strength = strengthMaxBase;

        this.priceOfAttackBase = priceOfAttackBase;
        priceOfAttack = priceOfAttackBase;

        this.agilityMax = agilityMax;
        this.armorMultiplier = armorMultiplier;
    }

    public void addPriceOfAttack(int number){
        priceOfAttack += number;
    }

    public void minusPriceOfAttack(int number){
        priceOfAttack -= number;
        if(priceOfAttack < priceOfAttackBase) priceOfAttack = priceOfAttackBase;
    }

    public void addStrengthMax(int number){
        strengthMax += number;
    }

    public void minusStrengthMax(int number){
        strengthMax -= number;
        if(strengthMax < strengthMaxBase) strengthMax = strengthMaxBase;
    }

    public void addDamage(int number){
        damageMin += number;
        damageMax += number;
    }

    public void minusDamage(int count){
        damageMin -= count;
        damageMax -= count;
        if (damageMin < damageBaseMin) damageMin = damageBaseMin;
        if (damageMax < damageBaseMax) damageMax = damageBaseMax;
    }

    public void addArmor(int count){
        armor += count;
    }

    public void minusArmor(int count){
        armor -= count;
    }

    public void addStrength(int number){
        strength += number;
    }

    public void minusStrength(int number){
        strength -= number;
        if(strength < 0) strength = 0;
    }

    public void addAgility(int number){
        agility += number;
        if(agility > agilityMax) agility = agilityMax;
    }

    public void minusAgility(int number){
        agility -= number;
        if(agility < 0) agility = 0;
    }

    public void addActionPoints(int number){
        actionPointsCurrent += number;
        if(actionPointsCurrent > actionPointsMax) actionPointsCurrent = actionPointsMax;
    }

    public boolean isEnoughActionPoints(int number){
        return actionPointsCurrent - number >= 0;
    }

    public void minusActionPoints(int number){
        actionPointsCurrent -= number;
    }


    public void addActionPointsMax(int number){
        actionPointsMax += number;
    }

    public void minusActionPointsMax(int number){
        actionPointsMax -= number;
        if(actionPointsMax < actionPointsBase) actionPointsMax = actionPointsBase;
    }

    //region Getters


    public int getPriceOfAttack() {
        return priceOfAttack;
    }

    public int getPriceOfAttackBase() {
        return priceOfAttackBase;
    }

    public int getStrengthMaxBase() {
        return strengthMaxBase;
    }

    public int getStrengthMax() {
        return strengthMax;
    }

    public int getDamageBaseMin() {
        return damageBaseMin;
    }

    public int getDamageBaseMax() {
        return damageBaseMax;
    }

    public int getActionPointsBase() {
        return actionPointsBase;
    }

    public int getDamageMin() {
        return damageMin;
    }

    public int getDamageMax() {
        return damageMax;
    }

    public int getDamageCurrent() {
        return random.nextInt(damageBaseMax - damageMin) + damageBaseMin;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getAgilityMax() {
        return agilityMax;
    }

    public int getArmor() {
        return armor;
    }

    public int getArmorMultiplier() {
        return armorMultiplier;
    }

    public int getActionPointsCurrent() {
        return actionPointsCurrent;
    }

    public int getActionPointsMax() {
        return actionPointsMax;
    }
    //endregion
}
