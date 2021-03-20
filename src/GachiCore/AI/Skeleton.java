package GachiCore.AI;

import GachiCore.Components.Items.Equipment.Bow;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Enemy;

public class Skeleton extends Enemy implements AIUser {
    public Skeleton(Stats stats, Inventory inventory, boolean isRange) {
        super(stats, inventory, isRange);
        Bow bow = new Bow();
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
