package GachiCore.Entities.Base;

import GachiCore.Components.Buffs.Base.Buff;
import GachiCore.Components.Buffs.Base.BuffRefreshType;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;

public abstract class Entity {
    private String name, description;

    private Entity enemy;
    protected Stats stats;
    protected Inventory inventory;

    protected ArrayList<Buff> once = new ArrayList<>();
    protected ArrayList<Buff> eachAttack = new ArrayList<>();
    protected ArrayList<Buff> eachTurn = new ArrayList<>();

    private Random random = new Random();
    private int additionalArmor = 0;

    private Consumer<Entity> entityConsumer = null;

    public Entity(Stats stats, Inventory inventory) {
        this.stats = stats;
        this.inventory = inventory;
        inventory.setOwner(this);
    }

    public void nextTurn(){
        takeOnBuffsOnTurn();
        beforeNextTurn();
        takeOffBuffsOnTurn();
    }

    protected void beforeNextTurn(){
        additionalArmor = stats.getActionPointsCurrent() * stats.getArmorMultiplier();
        stats.addArmor(additionalArmor);
        stats.minusActionPoints(stats.getActionPointsCurrent());
    }

    public void newTurn(){
        stats.minusArmor(additionalArmor);
        additionalArmor = 0;
        stats.addActionPoints(stats.getActionPointsMax());
        beforeNewTurn();
    }

    public void beforeNewTurn(){

    }

    public void heal(int number){
        stats.addStrength(number);
    }

    public void attack(){
        if(enemy == null) return;
        //TODO удали

        if(!stats.isEnoughActionPoints(stats.getPriceOfAttack())) return;
        takeOnBuffsOnAttack();
        enemy.takeDamage(stats.getDamageCurrent());
        stats.minusActionPoints(stats.getPriceOfAttack());

        takeOffBuffsOnAttack();
    }

    public void afterAttack(int damage){}

    public void takeDamage(int damage){
        if(tryDodge()) return;
        int pureDamage = persist(damage);
        stats.minusStrength(pureDamage);
        if(!isAlive()) death();
    }

    protected void death(){
        if(entityConsumer != null)
            entityConsumer.accept(this);
    }

    public void addActionAfterDeath(Consumer<Entity> entityConsumer){
        this.entityConsumer = entityConsumer;
    }

    protected boolean tryDodge(){
        return random.nextInt(100) + 1 < stats.getAgility();
    }

    protected int persist(int damage){
        int result = damage - stats.getArmor();
        return result < 0 ? 0 : result;
    }

    public void addBuff(Buff buff){
        buff.setTarget(this);
        if(buff.isBuff(BuffRefreshType.ONCE)) addOnceBuff(buff);
        if(buff.isBuff(BuffRefreshType.EACH_ATTACK)) eachAttack.add(buff);
        if(buff.isBuff(BuffRefreshType.EACH_TURN)) eachTurn.add(buff);

    }

    public void removeBuff(Buff buff){
        if(once.contains(buff)) once.remove(buff);
        if(eachAttack.contains(buff)) eachAttack.remove(buff);
        if(eachTurn.contains(buff)) eachTurn.remove(buff);
    }

    private void addOnceBuff(Buff buff){
        buff.takeOn(BuffRefreshType.ONCE);
        once.add(buff);
    }

    private void takeOnBuffs(BuffRefreshType refreshType, ArrayList<Buff> buffs){
        buffs.forEach((Buff buff) -> buff.takeOn(refreshType));
    }
    private void takeOffBuffs(BuffRefreshType refreshType, ArrayList<Buff> buffs){
        buffs.forEach((Buff buff) -> buff.takeOff(refreshType));
    }

    private void takeOnBuffsOnAttack(){
        takeOnBuffs(BuffRefreshType.EACH_ATTACK, eachAttack);
    }
    private void takeOffBuffsOnAttack(){
        takeOffBuffs(BuffRefreshType.EACH_ATTACK, eachAttack);
    }
    private void takeOnBuffsOnTurn(){
        takeOnBuffs(BuffRefreshType.EACH_TURN, eachTurn);
    }
    private void takeOffBuffsOnTurn(){
        takeOffBuffs(BuffRefreshType.EACH_TURN, eachTurn);
    }

    //region Getters

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isAlive() {
        return stats.getStrength() > 0;
    }

    public Stats getStats() {
        return stats;
    }

    public Entity getEnemy() {
        return enemy;
    }

    public Inventory getInventory() {
        return inventory;
    }

    //endregion
    //region Setters

    public void setName(String name) {
        this.name = name;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    protected void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }
    //endregion
}