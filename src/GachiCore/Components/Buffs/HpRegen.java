package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class HpRegen extends Buff {
    private int hpRegen;
    public HpRegen(int hpRegen) {
        this.hpRegen = hpRegen;
        refreshEachTurn = true;
    }

    @Override
    protected void takeOnEachTurn() {
        if(target.getStats().getStrength() == target.getStats().getStrengthMax()) return;
        target.heal(hpRegen);

    }
}
