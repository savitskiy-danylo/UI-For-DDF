package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class HealPotionT2 extends Buff {
    {
        refreshOnce = true;
    }

    @Override
    protected void takeOnOnce() {
        target.heal(30);
        removeBuff();
    }
}
