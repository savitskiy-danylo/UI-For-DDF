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
    private int actionPoints, actionPointsMax;
    private final int gachiPowerMax, gachiPowerExchangePrice; //TODO стоил ли делать gachiPowerMax константой?
    private int gachiPower;
    private int money;
    private int range = 1, position = 1; //TODO реализовать дальность
    protected Entity enemy;
    protected Inventory inventory;
    protected static MessageBox messageBox = MessageBox.getInstance(); //TODO не забудь проверить статическое это поле
    protected final ArrayList<Buff> buffs = new ArrayList<>();
    protected final ArrayList<SuperAttack> superAttacks = new ArrayList<>();
    protected SuperAttack currentSuperAttack;
    protected static final Random random = new Random(); //TODO не забудь проверить статическое это поле

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
        if(canAttack()) subtractActionPoints(priceOfAttack);
        refreshCurrentDamage();
        enemy.takeDamage(damageCurrent);
        addGachiPower(priceOfAttack*gachiPowerExchangePrice);
    }

    public boolean canAttack(){
        boolean enoughActionPoints = isEnoughActionPoints(priceOfAttack);
        //TODO сообщение о нехватки ОД.
        boolean enoughRange = range >= position;
        //TODO сообщение о нехватки дальности.
        return enoughActionPoints && enoughRange;
    }
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
    public int getActionPointsMax(){ return  actionPointsMax; }
    public void addActionPointsMax(int points){ actionPointsMax += points; }
    public void addActionPoints(int points){
        actionPoints += points;
        if(actionPoints > actionPointsMax) actionPoints = actionPointsMax;
    }
    public void subtractActionPointsMax(int points) {
        actionPointsMax -= points;
        if(actionPointsMax < actionPointsBase) actionPointsMax = actionPointsBase;
    }

    public void subtractActionPoints(int points) {
        if(!isEnoughActionPoints(points)) return;
        actionPoints -= points;
    }

    public boolean isEnoughActionPoints(int price){
        return actionPoints - price >= 0;
    }

    public boolean isUseActionPointsForArmor(){ return useActionsPointForArmor; }
    public void changeFlagActionPointsForArmor(){ useActionsPointForArmor = !useActionsPointForArmor; }
    //endregion

    //region GP, супер-атака, перевод ОД в GP.
    public void useSuperAttack(int price) {
        refreshCurrentDamageBase();
        if(currentSuperAttack != null && currentSuperAttack.canUse(actionPoints)) return;
        enemy.takeDamage(currentSuperAttack.getDamage(damageCurrentBase));
        //TODO Нужно реализовать и подключить супер-техники
    }

    public void setSuperAttack(String superAttackName){
        for (var attack :
                superAttacks) {
            if(attack.name.equals(superAttackName)){
                currentSuperAttack = attack;
                break;
            }
        }
        //TODO ERROR?
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
    public void buy(int price, Item item){
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

    //region Работа с врагами
    public void setEnemy(Entity enemy){ this.enemy = enemy; }
    public boolean canAttackThis(Entity entity){
        Entity data = this.enemy;
        enemy = entity;
        boolean result = canAttack();
        enemy = data;
        return result;
    }
    //endregion

    //region Инвентарь
    public Inventory getInventory() { return inventory; }
    //endregion

    public void nextTurn(){
        if(isDead) new Exception("Мертвец восстал");
        actionPoints = actionPointsMax;
    }
}
