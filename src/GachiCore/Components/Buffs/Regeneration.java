package GachiCore.Components.Buffs;

import GachiCore.Components.Buffs.Base.Buff;

public class Regeneration extends Buff {
    private int regeneration = 0;
    public Regeneration(int regeneration) {
        refreshEachTurn = true;
        this.regeneration = regeneration;
    }
    @Override
    protected void takeOnEachTurn() {
        target.getStats().addStrength(regeneration);
    }
}
