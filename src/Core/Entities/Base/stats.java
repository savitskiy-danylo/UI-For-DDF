package Core.Entities.Base;

import Core.Components.Base.Buff;

public interface stats {
    public int getHp();
    public int getHpMax();
    public int getDamageBaseMin();
    public int getDamageBaseMax();
    public int getDamageBaseCurrent();
    public int getDamageCurrent();
    public int getPriceOfAttack();
    public int getArmor();
    public int getArmorMultiplier();
    public int getEvasion();
    public int getEvasionMax();
    public int getActionPoints();
    public int getActionPointsMax();
    public int getMoney();
    public int getEvasionBase();
    public void heal(int points);
    public void addPriceOfAttack(int points);
    public void minusPriceOfAttack(int points);
    public void addDamage(int points);
    public void minusDamage(int points);
    public void addArmor(int points);
    public void addEvasion(int points);
    public void addActionPoints(int points);
    public void minusAP(int points);
    public boolean isEnoughAP(int price);
    public void addBuff(Buff buff);
    public void removeBuff(Buff buff);
}
