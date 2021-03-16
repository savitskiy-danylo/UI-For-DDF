package GachiCore.Components.Items.Consumables;

import GachiCore.Components.Buffs.HealConsumable;
import GachiCore.Components.Items.Consumables.Base.Consumable;

public class BottleOfSemen extends Consumable {
    public BottleOfSemen() {
        super("Bottle of semen", "Mmm... semen", 15, 7);
        buffs.add(new HealConsumable());
    }
}
