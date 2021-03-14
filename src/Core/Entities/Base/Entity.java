package Core.Entities.Base;

import Controllers.MBox.Message;
import Controllers.MBox.MessageBox;
import Core.Components.Base.Buff;
import Core.Components.Base.Inventory;

import java.util.ArrayList;
import java.util.Random;

public abstract class Entity implements stats{
    private Entity enemy;
    protected Inventory inventory;
    protected ArrayList<Buff> buffs = new ArrayList<>();
    private Random random = new Random();
    protected MessageBox messageBox = MessageBox.getInstance();

    private String name, description;
    private int hp, hpMax;
    private int damageBaseMin, damageBaseMax;
    private int damageBaseCurrent, damageCurrent;
    private int priceOfAttack, priceOfAttackMin;
    private int armor, armorMultiplier;
    private int evasion, evasionMax, evasionBase;
    private int actionPoints, actionPointsMax;
    private int money;
    private boolean isDead = false;

    //region Messages
    protected Message successfulEvade = null;
    protected Message takenDamage = null;
    protected Message death = null;
    protected Message heal = null;
    protected Message plusAP = null;
    //endregion

    public void nextTurn(){
        armor = 0;
        actionPoints = actionPointsMax;
        evasion = 0;
    }

    //region Взаимодействие с врагами
    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }
    public void takeDamage(int damage){
        if(tryEvade()){
            messageBox.addNewMessage(successfulEvade);
            return;
        }
        int pureDamage = persist(damage);
        hp -= pureDamage;
        messageBox.addNewMessage(takenDamage);
        if(hp <= 0){
            isDead = true;
            messageBox.addNewMessage(death);
        }
    }

    public void attack(){
        if(!canAttack()) return;
        refreshCurrentDamage();
        enemy.takeDamage(damageCurrent);
    }

    private void refreshCurrentDamageBase(){
        int range = damageBaseMax - damageBaseMin;
        damageBaseCurrent = random.nextInt(range+1) + damageBaseMin;
        damageCurrent = damageBaseCurrent;
    }
    private void refreshCurrentDamage(){
        refreshCurrentDamageBase();
        refreshBuffs();
    }

    protected void refreshBuffs(){
        for (var buff :
                buffs) {
            buff.use();
        }
    }

    public boolean canAttack(){
        return isEnoughAP(priceOfAttack);
    }

    private boolean tryEvade(){
        return random.nextInt(100) + 1 < evasion;
    }

    protected int persist(int damage){
        int result = damage - armor + (armorMultiplier * actionPoints);
        actionPoints = 0;
        return result < 0 ? 0 : result;
    }

    //endregion

    //region Х-ки.

    public void addBuff(Buff buff){
        buffs.add(buff);
        refreshBuffs();
    }

    public void removeBuff(Buff buff){
        if(buffs.contains(buff)) buffs.remove(buff);
        refreshBuffs();
    }

    public void heal(int points){
        hp += points;
        if(hp > hpMax) hp = hpMax;
        messageBox.addNewMessage(heal);
    }

    public void addPriceOfAttack(int points){
        priceOfAttack += points;
    }

    public void minusPriceOfAttack(int points){
        priceOfAttack -= points;
        if(priceOfAttack < priceOfAttackMin) priceOfAttack = priceOfAttackMin;
    }

    public void addDamage(int points){
        damageCurrent += points;
    }

    public void minusDamage(int points){
        damageCurrent -= points;
        if(damageCurrent < 0) damageCurrent = 0;
    }

    public void addArmor(int points){
        armor += points;
    }

    public void addEvasion(int points){
        evasion += points;
        if(evasion > evasionMax) evasion = evasionMax;
    }

    public void addActionPoints(int points){
        actionPoints += points;
        if(actionPoints > actionPointsMax) actionPoints = actionPointsMax;
        messageBox.addNewMessage(plusAP);
    }

    public boolean isEnoughAP(int price){
        return actionPoints - price >= 0;
    }

    public void minusAP(int points){
        if(!isEnoughAP(points)) return;
        actionPoints -= points;
    }
    //endregion

    //region Getters

    public int getEvasionBase() {
        return evasionBase;
    }

    public Entity getEnemy() {
        return enemy;
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

    public String getDescription() {
        return description;
    }

    public int getHp() {
        return hp;
    }

    public int getHpMax() {
        return hpMax;
    }

    public int getDamageBaseMin() {
        return damageBaseMin;
    }

    public int getDamageBaseMax() {
        return damageBaseMax;
    }

    public int getDamageBaseCurrent() {
        return damageBaseCurrent;
    }

    public int getDamageCurrent() {
        return damageCurrent;
    }

    public int getPriceOfAttack() {
        return priceOfAttack;
    }

    public int getArmor() {
        return armor;
    }

    public int getArmorMultiplier() {
        return armorMultiplier;
    }

    public int getEvasion() {
        return evasion;
    }

    public int getEvasionMax() {
        return evasionMax;
    }

    public int getActionPoints() {
        return actionPoints;
    }

    public int getActionPointsMax() {
        return actionPointsMax;
    }

    public int getMoney() {
        return money;
    }
    //endregion
    //region Setters (protected)

    protected void setEvasionBase(int evasionBase) {
        this.evasionBase = evasionBase;
    }

    protected void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    protected void setPriceOfAttackMin(int priceOfAttackMin) {
        this.priceOfAttackMin = priceOfAttackMin;
    }

    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    protected void setHpMax(int hpMax) {
        this.hpMax = hpMax;
    }

    protected void setDamageBaseMin(int damageBaseMin) {
        this.damageBaseMin = damageBaseMin;
    }

    protected void setDamageBaseMax(int damageBaseMax) {
        this.damageBaseMax = damageBaseMax;
    }

    protected void setDamageBaseCurrent(int damageBaseCurrent) {
        this.damageBaseCurrent = damageBaseCurrent;
    }

    protected void setDamageCurrent(int damageCurrent) {
        this.damageCurrent = damageCurrent;
    }

    protected void setPriceOfAttack(int priceOfAttack) {
        this.priceOfAttack = priceOfAttack;
    }

    protected void setArmor(int armor) {
        this.armor = armor;
    }

    protected void setArmorMultiplier(int armorMultiplier) {
        this.armorMultiplier = armorMultiplier;
    }

    protected void setEvasion(int evasion) {
        this.evasion = evasion;
    }

    protected void setEvasionMax(int evasionMax) {
        this.evasionMax = evasionMax;
    }

    protected void setActionPointsMax(int actionPoints){
        actionPointsMax =actionPoints;
    }

    protected void setMoney(int money) {
        this.money = money;
    }
    //endregion
}
