package GachiCore.AI;

import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Enemy;

public class GymMan extends Enemy implements AIUser {
    public GymMan(Stats stats, Inventory inventory, boolean isRange) {
        super(stats, inventory, isRange);
    }

    @Override
    public void turn() {
        attack();
    }

    @Override
    public boolean wantSwap() {
        return false;
    }

    @Override
    public boolean wantAgreeWithSwap() {
        return false;
    }
}
