package GachiCore.Components.Items.Equipment;

import GachiCore.Components.Buffs.BloodyKnifeBuff;
import GachiCore.Components.Items.Equipment.Base.Equipment;
import GachiCore.Components.Items.Equipment.Base.EquipmentType;
import GachiCore.Components.Items.Equipment.Base.Quality;

public class BloodyKnife extends Equipment {
    public BloodyKnife(){
        super("BloodyKnife", "Cute knife, can deal crit damage\ndamage + 25; critchance 15%", 200, 100, EquipmentType.WEAPON);
        setQuality(Quality.THREE);
        buffs.add(new BloodyKnifeBuff());
    }
}

