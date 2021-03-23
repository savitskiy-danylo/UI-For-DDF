package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class LeatherArmorBuff extends Buff {
    public LeatherArmorBuff(){refreshOnce=true;}
    @Override
    protected void takeOnOnce() {
        target.getStats().addStrengthMax(30);
        target.getStats().addArmor(7);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusStrengthMax(30);
        target.getStats().minusArmor(7);
    }
}
