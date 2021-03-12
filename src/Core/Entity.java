package Core;

public abstract class Entity {
    private Entity enemy;
    private final int strengthBase, agilityBase, actionPointsBase, armorBase, damageBase, gachiPowerBase;
    private int strengthGreen, agilityGreen, actionPointsGreen, armorGreen, damageGreen, gachiPowerGreen;
    private final Inventory inventory;
    private final String name;


    public Entity(int strengthBase, int agilityBase, int actionPointsBase, int armorBase, int damageBase,
                  int gachiPowerBase, Inventory inventory, String name) {
        this.strengthBase = strengthBase;
        this.agilityBase = agilityBase;
        this.actionPointsBase = actionPointsBase;
        this.armorBase = armorBase;
        this.damageBase = damageBase;
        this.gachiPowerBase = gachiPowerBase;
        this.inventory = inventory;
        this.name = name;
    }

    public void setStrengthGreen(int strengthGreen) {
        this.strengthGreen = strengthGreen;
    }

    public void setAgilityGreen(int agilityGreen) {
        this.agilityGreen = agilityGreen;
    }

    public void setActionPointsGreen(int actionPointsGreen) {
        this.actionPointsGreen = actionPointsGreen;
    }

    public void setArmorGreen(int armorGreen) {
        this.armorGreen = armorGreen;
    }

    public void setDamageGreen(int damageGreen) {
        this.damageGreen = damageGreen;
    }

    public void setGachiPowerGreen(int gachiPowerGreen) {
        this.gachiPowerGreen = gachiPowerGreen;
    }

    public int getStrengthGreen() {
        return strengthGreen;
    }

    public int getAgilityGreen() {
        return agilityGreen;
    }

    public int getActionPointsGreen() {
        return actionPointsGreen;
    }

    public int getArmorGreen() {
        return armorGreen;
    }

    public int getDamageGreen() {
        return damageGreen;
    }

    public int getGachiPowerGreen() {
        return gachiPowerGreen;
    }

    public int getStrengthBase() {
        return strengthBase;
    }

    public int getAgilityBase() {
        return agilityBase;
    }

    public int getActionPointsBase() {
        return actionPointsBase;
    }

    public int getArmorBase() {
        return armorBase;
    }

    public int getDamageBase() {
        return damageBase;
    }

    public int getGachiPowerBase() {
        return gachiPowerBase;
    }

    public Entity getEnemy() {
        return enemy;
    }

    public void setEnemy(Entity enemy) {
        this.enemy = enemy;
    }

    public abstract void nextTurn();

    public Inventory getInventory() {
        return inventory;
    }
}
