package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.BowBuff;
import GachiCore.Components.Buffs.LeatherArmorBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class LeatherArmor extends Equipment {
    public LeatherArmor() {
        super("LeatherArmor", "Really cool\nhp + 10; armor + 2;", 30, 15, EquipmentType.ARMOR);
        setQuality(Quality.ONE);
        buffs.add(new LeatherArmorBuff());

    }
}
