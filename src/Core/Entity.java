package Core;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity {
    private String name;
    private final int strengthBase, strengthMultiplier;
    private int strength;
    private final int hpBase;
    private int hp, hpMax;
    protected boolean isDead = false;
    private final int agilityBase, agilityMultiplier;
    private int agility;
    private final int dodgesMax;
    private int dodges;
    private final int damageBaseMin, damageBaseMax;
    private int damageCurrentBase, damageCurrent;
    private int priceOfAttack;
    private final int armorMultiplier;
    private int armor;
    private boolean useActionsPointForArmor = false;
    private final int actionPointsBase;
    private int actionPoints;
    private final int gachiPowerMax, gachiPowerExchangePrice; //TODO стоил ли делать gachiPowerMax константой?
    private int gachiPower;
    private int money;
    private final int range; //TODO реализовать дальность
    protected Entity enemy;
    protected Inventory inventory;
    protected static final Random random = new Random(); //TODO не забудь проверить статическое это поле
    protected final ArrayList<Buff> buffs = new ArrayList<>();

    public Entity(int strengthBase, int strengthMultiplier, int hpBase,
                  int agilityBase, int agilityMultiplier, int dodgesMax,
                  int damageBaseMin, int damageBaseMax, int armorMultiplier,
                  int actionPointsBase, int gachiPowerMax, int gachiPowerExchangePrice,
                  int range) {
        this.strengthBase = strengthBase;
        this.strengthMultiplier = strengthMultiplier;
        this.hpBase = hpBase;
        this.agilityBase = agilityBase;
        this.agilityMultiplier = agilityMultiplier;
        this.dodgesMax = dodgesMax;
        this.damageBaseMin = damageBaseMin;
        this.damageBaseMax = damageBaseMax;
        this.armorMultiplier = armorMultiplier;
        this.actionPointsBase = actionPointsBase;
        this.gachiPowerMax = gachiPowerMax;
        this.gachiPowerExchangePrice = gachiPowerExchangePrice;
        this.range = range;
        //TODO конструктор переписать
    }

    //region Сила, хп, хилл, получение урона
    public int getStrengthBase() { return strengthBase; }
    public int getStrength() {return  strength; }
    public int getHpBase() { return hpBase; }
    public int getHpMax() { return hpMax; }
    public void addStrength(int strength){
        this.strength += strength;
        changeHpMax(strength);
    }
    public void subtractStrength(int strength){
        this.strength -= strength;
        changeHpMax(-strength);
    }

    private void changeHpMax(int changeOn){
        hpMax += changeOn * strengthMultiplier;
        if(hpMax < hpBase) hpMax = hpBase;
        if(hpMax < hp) hp = hpMax;
    }

    public void heal(int hp){
        this.hp += hp;
        if(this.hp > hpMax) this.hp = hpMax;
        //TODO сообщение про отхил
    }

    public void takeDamage(int damage){
        if(tryDodge()) return;
        //TODO сообщения про полученый урон / уклон от атаки
        int pureDamage = persist(damage);
        hp -= pureDamage;
        if(hp < 1) {
            isDead = true;
            //TODO Подумай насчет удаления существа из очереди
        }
    }
    //endregion

    //region Ловкость, уклонения, попытка увернуться.
    public int getAgilityBase() {
        return agilityBase;
    }

    public int getAgility() {
        return agility;
    }

    public int getDodgesMax() {
        return dodgesMax;
    }

    public int getDodges() {
        return dodges;
    }

    private boolean tryDodge(){
        return random.nextInt(101) <= dodges;
    }

    public void addAgility(int agility){
        this.agility += agility;
        changeDodges(agility);
    }

    public void subtractAgility(int agility){
        this.agility -= agility;
        changeDodges(-agility);
    }

    private void changeDodges(int agility){
        dodges += agility * agilityMultiplier;
        if(dodges > dodgesMax) dodges = dodgesMax;
        if(dodges < 0) dodges = 0;
    }
    //endregion

    //region Урон, атака, накладывание бафов, стоимость атаки, можно ли атаковать.
    public int getDamageCurrentBase(){ return damageCurrentBase; }
    public int getCurrentDamage(){ return damageCurrent;}
    public int getPriceOfAttack() { return priceOfAttack; }
    public void raisePriceOfAttack(int price) { priceOfAttack += price; }
    public void lowerPriceOfAttack(int price){ priceOfAttack -= price; }

    public void attack(){
        //TODO сообщение о нехватки ОД.
        if(canAttack()) actionPoints -= priceOfAttack;
        refreshCurrentDamage();
        enemy.takeDamage(damageCurrent);
        //TODO плюс GP за атаку
    }

    public boolean canAttack(){ return priceOfAttack - actionPoints >= 0; }
    public void addCurrentDamage(int damage){ damageCurrent += damage; }
    public void subtractDamage(int damage){
        damageCurrent -= damage;
        if(damageCurrent < 0) damageCurrent = 0;
    }
    private void refreshCurrentDamageBase(){
        int range = damageBaseMax - damageBaseMin;
        damageCurrentBase = random.nextInt(range+1);
    }

    private void refreshCurrentDamage(){
        refreshCurrentDamageBase();
        refreshBuffs();
    }

    private void refreshBuffs(){
        for (var buff :
                buffs) {
            buff.use();
        }
    }
    //endregion

    //region Броня, защита.
    public int getArmor() {return armor; }
    public void addArmor(int armor){ this.armor += armor; }
    public void subtractArmor(int armor){ this.armor -= armor; }
    private int persist(int damage){
        int additionalArmor = 0;
        if(useActionsPointForArmor){
            additionalArmor = armor * (actionPoints * armorMultiplier);
        }
        int result = damage - (armor + additionalArmor);
        return result < 0 ? 0 : result;
    }
    //endregion

    //region ОД, флаг использование од.
    public int getActionPoints(){ return actionPoints; }
    public void addActionPoints(int points){ actionPoints += points; }
    public void subtractActionPoints(int points) {
        actionPoints -= points;
        if(actionPoints < actionPointsBase) actionPoints = actionPointsBase;
    }
    public boolean isUseActionPointsForArmor(){ return useActionsPointForArmor; }
    public void changeFlagActionPointsForArmor(){ useActionsPointForArmor = !useActionsPointForArmor; }
    //endregion

    //region GP, супер-атака, перевод ОД в GP.
    public boolean canUseSuperAttack(int price){ return gachiPower - price >= 0; }
    public void useSuperAttack(int price) {
        //TODO сообщение про нехватку GP
        if(!canUseSuperAttack(price)) return;
        //TODO Нужно реализовать и подключить супер-техники
    }

    public void addGachiPower(int power){
        gachiPower += power;
        if(gachiPower > gachiPowerMax) gachiPower = gachiPowerMax;
    }

    public void subtractGachiPower(int power){
        gachiPower -= power;
        if(gachiPower < 0) gachiPower = 0;
    }

    private void refreshGachiPower(){
        if(useActionsPointForArmor) return;
        int result = actionPoints * gachiPowerExchangePrice;
        addGachiPower(result);
        //TODO сообщение о переводе очков в GP
    }
    //endregion

    //region Операции с деньгами
    public void buy(int price){
        if(!canBuy(price)) return;
        money -= price;
    }

    public boolean canBuy(int price){
        return money - price > 0;
    }

    public void addMoney(int plusMoneys){
        money += plusMoneys;
    }
    //endregion
    //region Имя
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    //endregion

    public Inventory getInventory() { return inventory; }
    public void setInventory(Inventory inventory) { this.inventory = inventory; }
    public void addItem(Item item){
        inventory.addItem(item);
    }

}
