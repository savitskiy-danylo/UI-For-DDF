package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class GreaseConsumable extends Buff {
    {
        refreshOnce = true;
        refreshEachTurn = true;
    }

    @Override
    protected void takeOnOnce(){
        target.getStats().addActionPoints(2);
        target.getStats().addArmor(5);
        target.getStats().addDamage(10);
        target.getStats().minusStrength(7);

    }

    @Override
    protected void takeOnEachTurn() {
        target.getStats().minusActionPoints(2);
        target.getStats().minusArmor(5);
        target.getStats().minusDamage(10);
        target.getStats().addStrength(7);
        removeBuff();
    }
}
