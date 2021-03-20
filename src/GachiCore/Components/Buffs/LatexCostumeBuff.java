package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class LatexCostumeBuff extends Buff {
    public LatexCostumeBuff() {
        refreshOnce = true;
    }

    @Override
    protected void takeOnOnce() {
        target.getStats().addStrength(50);
        target.getStats().addArmor(2);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusStrength(50);
        target.getStats().minusArmor(2);
    }
}
