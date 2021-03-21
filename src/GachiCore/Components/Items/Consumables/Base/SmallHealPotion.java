package GachiCore.Components.Items.Consumables.Base;

import GachiCore.Components.Buffs.HealPotionT1;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class SmallHealPotion extends Consumable{
    public SmallHealPotion(){
        super("SmallHealPotion", "Heal 15hp", 20, 10);
        setQuality(Quality.ONE);
        buffs.add(new HealPotionT1());
    }
}
