package Core;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Controllers.MBox.MessageType;
import Core.Components.Base.Buff;
import Core.Components.Base.Inventory;
import Core.Components.Base.Usable.Item;
import Core.Components.Base.SuperAttack;

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
    private final int gachiPowerMax, gachiPowerExchangePrice;
    private int gachiPower;
    private int money;

    private int range = 1;

    private int position = 1;
    protected Entity enemy;
    protected Inventory inventory;
    protected final ArrayList<Buff> buffs = new ArrayList<>();
    protected final ArrayList<SuperAttack> superAttacks = new ArrayList<>();
    protected SuperAttack currentSuperAttack = null;
    protected final Random random = new Random(); //TODO не забудь проверить статическое это поле

    //region Сообщения
    protected static MessageBox messageBox = MessageBox.getInstance(); //TODO не забудь проверить статическое это поле
    protected Message healMessage = new Message("Healed on ", MessageType.POSITIVE);
    protected Message damageTakenMessage = new Message("You were damaged on ", MessageType.NEGATIVE);
    protected Message notEnoughAP = new Message("Not enough action points for attack", MessageType.NEGATIVE);
    protected Message notEnoughRange = new Message("Not enough range for attack", MessageType.NEGATIVE);
    protected Message transactionMessage = new Message("Action points was exchanged on gachi power", MessageType.NEUTRAL);
    protected Message successfulDodge = new Message("You dodge damage!", MessageType.POSITIVE);
    protected Message loseMessage = new Message("You were killed", MessageType.NEGATIVE);
    //endregion

    public Entity(int strengthBase, int strengthMultiplier, int hpBase,
                  int agilityBase, int agilityMultiplier, int dodgesMax,
                  int damageBaseMin, int damageBaseMax, int armorMultiplier,
                  int actionPointsBase, int gachiPowerMax, int gachiPowerExchangePrice,
                  int range) {
        this.strengthBase = strengthBase;
        this.strengthMultiplier = strengthMultiplier;
        this.hpBase = hpBase;
        hp = hpBase;
        hpMax = hpBase;
        this.agilityBase = agilityBase;
        actionPoints = actionPointsBase;
        actionPointsMax = actionPointsBase;
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
        messageBox.addNewMessage(messageWithOn(healMessage, Integer.toString(hp)));
    }
    private Message messageWithOn(Message message, String text){
        Message newMessage = new Message(message.getText(), message.getMessageType());
        newMessage.setMessage(newMessage.getText() + text);
        return newMessage;
    }

    public void takeDamage(int damage){
        if(tryDodge()){
            messageBox.addNewMessage(successfulDodge);
            return;
        }
        int pureDamage = persist(damage);
        hp -= pureDamage;
        messageBox.addNewMessage(messageWithOn(damageTakenMessage, Integer.toString(pureDamage)));
        if(hp < 1) {
            isDead = true;
            messageBox.addNewMessage(loseMessage);
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
        return random.nextInt(100) + 1 <= dodges;
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
        if(!canAttack()) return;
        subtractActionPoints(priceOfAttack);
        refreshCurrentDamage();
        enemy.takeDamage(damageCurrent);
        addGachiPower(priceOfAttack*gachiPowerExchangePrice);
    }

    public boolean canAttack(){
        boolean enoughActionPoints = isEnoughActionPoints(priceOfAttack);
        if(!enoughActionPoints) messageBox.addNewMessage(notEnoughAP);
        boolean enoughRange = range >= Math.abs(enemy.position - position);
        if(!enoughRange) messageBox.addNewMessage(notEnoughRange);
        return enoughActionPoints && enoughRange;
    }

    public void addCurrentDamage(int damage){ damageCurrent += damage; }

    public void subtractDamage(int damage){
        damageCurrent -= damage;
        if(damageCurrent < 0) damageCurrent = 0;
    }

    private void refreshCurrentDamageBase(){
        int range = damageBaseMax - damageBaseMin;
        damageCurrentBase = random.nextInt(range+1) + damageBaseMin;
        damageCurrent = damageCurrentBase;
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
    public void setPosition(int position) {
        this.position = position;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setPriceOfAttack(int priceOfAttack) {
        this.priceOfAttack = priceOfAttack;
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
    public void useSuperAttack() {
        refreshCurrentDamageBase();
        if(currentSuperAttack == null || !currentSuperAttack.canUse(actionPoints)) return; //TODO что с атакой?
        enemy.takeDamage(currentSuperAttack.getDamage(damageCurrentBase));
        //TODO Нужно реализовать и подключить супер-техники
    }

    public void setSuperAttack(String superAttackName){
        for (var attack :
                superAttacks) {
            if(attack.getName().equals(superAttackName)){
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
        messageBox.addNewMessage(transactionMessage);
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

    //region Buffs
    public void addBuff(Buff buff){
        buffs.add(buff);
    }

    public boolean containsBuff(Buff buff){
        return buffs.contains(buff);
    }

    public void removeBuff(Buff buff){
        if(containsBuff(buff)){
            buffs.remove(buff);
        }
    }
    //endregion

    public void nextTurn(){
        if(isDead) new Exception("Мертвец восстал");
        actionPoints = actionPointsMax;
    }

    public boolean isDead(){ return isDead; }
}
