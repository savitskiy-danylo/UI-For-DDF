package GachiCore.Entities.Base;

import GachiCore.Components.Items.Inventory;
import GachiCore.Components.Stats;

public class Enemy extends Entity{
    private boolean isRange = false;

    public Enemy(Stats stats, Inventory inventory, boolean isRange) {
        super(stats, inventory);
        this.isRange = isRange;
    }

    public boolean isRange() {
        return isRange;
    }
}
