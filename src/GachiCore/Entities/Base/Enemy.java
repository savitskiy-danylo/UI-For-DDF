package GachiCore.Entities.Base;

import GachiCore.Components.Inventory;
import GachiCore.Components.Stats;

public class Enemy extends Entity{
    private boolean isRange = false;
    private boolean wantSwap = false;
    private boolean wantSkip = false;

    public Enemy(Stats stats, Inventory inventory, boolean isRange, boolean wantSwap, boolean wantSkip) {
        super(stats, inventory);
        this.isRange = isRange;
        this.wantSwap = wantSwap;
        this.wantSkip = wantSkip;
    }

    public boolean isRange() {
        return isRange;
    }

    public boolean isWantSwap() {
        return wantSwap;
    }

    public boolean isWantSkip() {
        return wantSkip;
    }
}
