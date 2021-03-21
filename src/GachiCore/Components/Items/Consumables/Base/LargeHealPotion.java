package GachiCore.Components.Items.Consumables.Base;

import GachiCore.Components.Buffs.HealPotionT2;
import GachiCore.Components.Buffs.HealPotionT3;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class LargeHealPotion extends Consumable{
    public LargeHealPotion(){
        super("LargeHealPotion", "Heal 60hp", 80, 40);
        setQuality(Quality.THREE);
        buffs.add(new HealPotionT3());
    }
}
