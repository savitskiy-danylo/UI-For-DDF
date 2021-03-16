package GachiCore.Entities.Base;

import GachiCore.Components.Skills.Base.GachiPower;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;

public class GachiPowerUser extends Entity {
    private GachiPower gachiPower;
    private boolean useApForGp = true;

    public GachiPowerUser(Stats stats, Inventory inventory, GachiPower gachiPower) {
        super(stats, inventory);
        this.gachiPower = gachiPower;
        gachiPower.setGachiPowerUser(this);
    }

    public GachiPower getGachiPower() {
        return gachiPower;
    }

    public void setGachiPower(GachiPower gachiPower) {
        this.gachiPower = gachiPower;
    }

    @Override
    public void afterAttack(int damage) {
        gachiPower.addGachiPower(damage * gachiPower.getGachiPowerPerAttack());
    }

    @Override
    protected void beforeNextTurn() {
        if(!useApForGp) super.beforeNextTurn();
        gachiPower.addGachiPower(gachiPower.getGachiPowerPerActionPoint() * stats.getActionPointsCurrent());
        stats.minusActionPoints(stats.getActionPointsCurrent());
    }

    @Override
    public void beforeNewTurn() {
        super.beforeNewTurn();
    }
}
