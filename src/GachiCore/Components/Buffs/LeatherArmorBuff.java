package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class LeatherArmorBuff extends Buff {
    public LeatherArmorBuff(){refreshOnce=true;}
    @Override
    protected void takeOnOnce() {
        target.getStats().addStrength(10);
        target.getStats().addArmor(2);
    }

    @Override
    protected void takeOffOnce() {
        target.getStats().minusStrength(10);
        target.getStats().minusArmor(2);
    }
}
