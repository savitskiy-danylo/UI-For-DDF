package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.LatexCostumeBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;

public class LatexCostume extends Equipment {
    public LatexCostume() {
        super("LatexCostume", "Umm... nice", 150, 75, EquipmentType.ARMOR);
        buffs.add(new LatexCostumeBuff());
    }
}
