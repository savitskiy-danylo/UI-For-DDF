package GachiCore.Components.Items.Consumables;

import GachiCore.Components.Buffs.HealPotionT1;
import GachiCore.Components.Buffs.HealPotionT2;
import GachiCore.Components.Items.Consumables.Base.Consumable;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class MediumHealPotion extends Consumable {
    public MediumHealPotion(){
        super("MediumHealPotion", "Heal 30hp", 40, 20);
        setQuality(Quality.TWO);
        buffs.add(new HealPotionT2());
    }
}
