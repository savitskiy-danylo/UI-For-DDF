package GachiCore;

import java.util.ArrayList;
import java.util.Random;

public class Entity implements StatsInterface {
    private Entity enemy;
    protected Stats stats;

    private ArrayList<Buff> once = new ArrayList<>();
    private ArrayList<Buff> eachAttack = new ArrayList<>();
    private ArrayList<Buff> eachTurn = new ArrayList<>();

    private Random random = new Random();
    private int additionalArmor = 0;

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
    }

    public void heal(int number){
        stats.addStrength(number);
    }

    public void attack(){
        takeOnBuffsOnAttack();
        enemy.takeDamage(stats.getDamageCurrent());
        takeOffBuffsOnAttack();
    }

    public void takeDamage(int damage){
        if(tryDodge()) return;
        int pureDamage = persist(damage);
        stats.minusStrength(pureDamage);
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
        for (var buff:
                buffs
        ) {
            buff.takeOn(refreshType);
        }
    }
    private void takeOffBuffs(BuffRefreshType refreshType, ArrayList<Buff> buffs){
        for (var buff:
                buffs
        ) {
            buff.takeOff(refreshType);
        }
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

    public boolean isAlive() {
        return stats.getStrength() > 0;
    }

    public Stats getStats() {
        return stats;
    }

    public Entity getEnemy() {
        return enemy;
    }
    //endregion
    //region Setters
    protected void setStats(Stats stats) {
        this.stats = stats;
    }

    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }
    //endregion
}
