package Core.Entities.Base;

public interface advancedStats extends stats{
    public void addMoney(int coins);
    public void minusMoney(int coins);
    public boolean isEnoughMoney(int coins);
    public void addAgility(int points);
    public void addStrength(int points);
    public void minusAgility(int points);
    public void minusStrength(int points);
    public void addGachiPower(int points);
    public void minusGachiPower(int points);
    public boolean isEnoughGachiPower(int price);
    public int getAgility();
    public int getAgilityBase();
    public int getStrength();
    public int getStrengthBase();
    public int getGachiPower();
    public int getGachiPowerMax();
}
