package GachiCore.AI;

import GachiCore.Components.Buffs.SlaveRageBuff;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Enemy;

public class Slave extends Enemy implements AIUser{
    public Slave(Stats stats, Inventory inventory, boolean isRange) {
        super(stats, inventory, isRange);

    }
    @Override
    public void turn() {
        attack();
        attack(); //can attack twice because of leather armor ap+1 buff
    }
    @Override
    public boolean wantSwap() {
        return true;
    }

    @Override
    public boolean wantAgreeWithSwap() {
        return true;
    }
}
