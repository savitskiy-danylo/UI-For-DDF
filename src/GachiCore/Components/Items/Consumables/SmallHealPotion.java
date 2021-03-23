package GachiCore.Components.Items.Consumables;

import GachiCore.Components.Buffs.HealPotionT1;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class SmallHealPotion extends Consumable {
    public SmallHealPotion(){
        super("SmallHealPotion", "Heal 20hp", 20, 10);
        setQuality(Quality.ONE);
        buffs.add(new HealPotionT1());
    }
}
