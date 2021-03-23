package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class LatexCostumeBuff extends Buff {
    public LatexCostumeBuff() {
        refreshOnce = true;
    }

    @Override
    protected void takeOnOnce() {
        target.getStats().addStrengthMax(50);
        target.getStats().addArmor(20);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusStrengthMax(50);
        target.getStats().minusArmor(20);
    }
}
