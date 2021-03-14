package Core.EntitiesOLD.Base;

public abstract class Player extends Entity {

    public Player(int strengthBase, int strengthMultiplier, int hpBase, int agilityBase,
                  int agilityMultiplier, int dodgesMax, int damageBaseMin, int damageBaseMax,
                  int armorMultiplier, int actionPointsBase, int gachiPowerMax, int gachiPowerExchangePrice,
                  int range) {
        super(strengthBase, strengthMultiplier, hpBase, agilityBase,
                agilityMultiplier, dodgesMax, damageBaseMin, damageBaseMax,
                armorMultiplier, actionPointsBase, gachiPowerMax, gachiPowerExchangePrice,
                range);
    }

    @Override
    public boolean retreat() {
        return false;
    }
}
