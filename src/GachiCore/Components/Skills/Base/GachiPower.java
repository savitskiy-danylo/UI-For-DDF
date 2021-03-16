package GachiCore.Components.Skills.Base;

import GachiCore.Entities.Base.GachiPowerUser;

import java.util.ArrayList;

public class GachiPower {
    private GachiPowerUser gachiPowerUser;
    private int gachiPowerMax, gachiPowerCurrent;
    private int gachiPowerPerActionPoint, gachiPowerPerAttack;

    private ArrayList<Skill> skills = new ArrayList<>();

    public GachiPower(int gachiPowerMax, int gachiPowerCurrent,
                      int gachiPowerPerActionPoint, int gachiPowerPerAttack) {
        this.gachiPowerUser = gachiPowerUser;
        this.gachiPowerMax = gachiPowerMax;
        this.gachiPowerCurrent = gachiPowerCurrent;
        this.gachiPowerPerActionPoint = gachiPowerPerActionPoint;
        this.gachiPowerPerAttack = gachiPowerPerAttack;
    }

    public void addGachiPower(int count){
        gachiPowerCurrent += count;
        if(gachiPowerCurrent > gachiPowerMax) gachiPowerCurrent = gachiPowerMax;
    }

    public void minusGachiPower(int count){
        gachiPowerCurrent -= count;
        if(gachiPowerCurrent < 0) gachiPowerCurrent = 0;
    }

    public boolean isEnoughGachiPower(int price){ return gachiPowerCurrent - price >= 0; }

    public void addSkill(Skill skill){
        skill.setUser(gachiPowerUser);
        skills.add(skill);
    }

    public void removeSkill(Skill skill){
        skills.removeIf((Skill skillForRemove) -> skillForRemove == skill);
    }

    public void use(Skill skill){
        if(!isEnoughGachiPower(skill.getPrice())) return;
        skill.use();
        gachiPowerCurrent -= skill.getPrice();
    }

    public void setGachiPowerUser(GachiPowerUser gachiPowerUser) {
        this.gachiPowerUser = gachiPowerUser;
    }

    //region Getters
    public int getGachiPowerMax() {
        return gachiPowerMax;
    }

    public int getGachiPowerCurrent() {
        return gachiPowerCurrent;
    }

    public int getGachiPowerPerActionPoint() {
        return gachiPowerPerActionPoint;
    }

    public int getGachiPowerPerAttack() {
        return gachiPowerPerAttack;
    }
    //endregion
}