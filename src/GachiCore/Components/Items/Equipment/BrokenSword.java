package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.BrokenSwordBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;

public class BrokenSword extends Equipment {
    public BrokenSword() {
        super("Broken sword", "description",180, 90, EquipmentType.WEAPON);
        buffs.add(new BrokenSwordBuff());
    }
}
