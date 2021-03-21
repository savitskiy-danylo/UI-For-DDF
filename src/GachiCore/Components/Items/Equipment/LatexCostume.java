package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.LatexCostumeBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class LatexCostume extends Equipment {
    public LatexCostume() {
        super("LatexCostume", "Umm... nice\nhp + 50; armor + 20;", 150, 75, EquipmentType.ARMOR);
        setQuality(Quality.THREE);
        buffs.add(new LatexCostumeBuff());
    }
}
