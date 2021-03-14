package Core.Entities.Base;

public abstract class Boss extends Enemy implements advancedStats{
    private int agility, agilityMultiplier;
    private int agilityBase;
    private int strength, strengthMultiplier;
    private int strengthBase;
    private int gachiPower, gachiPowerMax;
    private boolean useActionPointsForArmor = false;



    @Override
    protected int persist(int damage) {
        if(useActionPointsForArmor) return super.persist(damage);
        int result = damage - getArmor();
        setActionPoints(0);
        return result < 0 ? 0 : result;
    }

    @Override
    public void nextTurn() {
        super.nextTurn();
        agility = 0;
        strength = 0;
    }

    public void addMoney(int coins){
        setMoney(getMoney() + coins);
    }

    public void minusMoney(int coins){
        int result = getMoney() - coins;
        if(!isEnoughMoney(coins)) return;
        setMoney(result);
    }

    public boolean isEnoughMoney(int coins){
        return getMoney() - coins >= 0;
    }

    public void addAgility(int points){
        agility += points;
        refreshAgility();
    }

    public void addStrength(int points){
        strength += points;
        refreshStrength();
    }

    public void minusAgility(int points){
        agility -= points;
        if(agility < agilityBase) agility = agilityBase;
        refreshAgility();
    }

    public void minusStrength(int points){
        strength -= points;
        if(strength < strengthBase) strength = strengthBase;
        refreshStrength();
    }

    protected void refreshAgility(){
        setEvasion(getEvasionBase() + agility * agilityMultiplier);
    }

    protected void refreshStrength(){
        setHpMax(getHpMax() + strength * strengthMultiplier);
    }

    public void addGachiPower(int points){
        gachiPower += points;
        if(gachiPower > gachiPowerMax) gachiPower = gachiPowerMax;
    }

    public void minusGachiPower(int points){
        if(!isEnoughGachiPower(points)) return;
        gachiPower -= points;
        if(gachiPower < 0) gachiPower = 0;
    }

    public boolean isEnoughGachiPower(int price){ return gachiPower - price > 0; }

    //region Setters
    protected void setAgility(int agility) {
        this.agility = agility;
    }

    protected void setAgilityMultiplier(int agilityMultiplier) {
        this.agilityMultiplier = agilityMultiplier;
    }

    protected void setAgilityBase(int agilityBase) {
        this.agilityBase = agilityBase;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }

    protected void setStrengthMultiplier(int strengthMultiplier) {
        this.strengthMultiplier = strengthMultiplier;
    }

    protected void setStrengthBase(int strengthBase) {
        this.strengthBase = strengthBase;
    }

    protected void setGachiPower(int gachiPower) {
        this.gachiPower = gachiPower;
    }

    protected void setGachiPowerMax(int gachiPowerMax) {
        this.gachiPowerMax = gachiPowerMax;
    }
    //endregion
    //region Getters
    public int getAgility() {
        return agility;
    }

    public int getAgilityBase() {
        return agilityBase;
    }

    public int getStrength() {
        return strength;
    }

    public int getStrengthBase() {
        return strengthBase;
    }

    public int getGachiPower() {
        return gachiPower;
    }

    public int getGachiPowerMax() {
        return gachiPowerMax;
    }

    public boolean isUseActionPointsForArmor() {
        return useActionPointsForArmor;
    }
    //endregion
}
