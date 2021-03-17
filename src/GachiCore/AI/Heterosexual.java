package GachiCore.AI;

import GachiCore.Components.Buffs.Regeneration;
import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;
import GachiCore.Entities.Base.Enemy;

public class Heterosexual extends Enemy implements AIUser {
    public Heterosexual(Stats stats, Inventory inventory, boolean isRange, int regeneration) {
        super(stats, inventory, isRange);
        Regeneration regen = new Regeneration(regeneration);
        regen.setTarget(this);
        regen.addBuff();
    }

    @Override
    public void turn() {
        while (getStats().isEnoughActionPoints(getStats().getPriceOfAttack())){
            attack();
        }
    }

    @Override
    public boolean wantSwap() {
        return (getStats().getStrength() / getStats().getStrengthMax()) * 100 < 40;
    }

    @Override
    public boolean wantSkip() {
        return true;
    }
}
