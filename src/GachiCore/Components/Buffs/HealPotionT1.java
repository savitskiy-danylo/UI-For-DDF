package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class HealPotionT1 extends Buff {
    {
        refreshOnce = true;
    }

    @Override
    protected void takeOnOnce() {
        target.heal(15);
        removeBuff();
    }
}
