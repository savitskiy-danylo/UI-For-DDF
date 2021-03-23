package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.BowBuff;
import GachiCore.Components.Buffs.LeatherArmorBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class LeatherArmor extends Equipment {
    public LeatherArmor() {
        super("LeatherArmor", "Really cool\nhp + 30; armor + 7;", 50, 25, EquipmentType.ARMOR);
        setQuality(Quality.ONE);
        buffs.add(new LeatherArmorBuff());
    }
}
